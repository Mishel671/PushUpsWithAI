package ru.dzyubamichael.pushupswithai.presentation.training.camera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.mlkit.common.MlKitException
import dagger.hilt.android.AndroidEntryPoint
import ru.dzyubamichael.pushupswithai.databinding.FragmentTrainingCameraBinding
import ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.posedetector.PoseDetectorProcessor
import ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.preference.PreferenceUtils

@AndroidEntryPoint
class TrainingCameraFragment : Fragment() {

    private var _binding: FragmentTrainingCameraBinding? = null
    private val binding: FragmentTrainingCameraBinding
        get() = _binding ?: throw RuntimeException("")

    private val viewModel: TrainingCameraViewModel by viewModels()

    private val args by navArgs<TrainingCameraFragmentArgs>()

    private var cameraProvider: ProcessCameraProvider? = null
    private var previewUseCase: Preview? = null
    private var analysisUseCase: ImageAnalysis? = null
    private var imageProcessor: PoseDetectorProcessor? = null
    private var needUpdateGraphicOverlayImageSourceInfo = false
    private var lensFacing = CameraSelector.LENS_FACING_FRONT
    private var cameraSelector: CameraSelector? = null
    private var totalPushUps = 0
    private var isLastExercise = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()
        _binding = FragmentTrainingCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseArgs()
        binding.facingSwitch.setOnClickListener {
            changeCamera()
        }
        setViewModelObserver()
        bindAnalysisUseCase()
    }

    private fun setViewModelObserver() {
        viewModel.setTotalPushUps(totalPushUps)
        binding.pushUpsProgress.max = totalPushUps
        viewModel.countPushUps.observe(viewLifecycleOwner) {
            binding.tvCount.text = it.toString()
            binding.pushUpsProgress.progress = it
        }
        viewModel.exerciseEnd.observe(viewLifecycleOwner) {
            if (isLastExercise) {
                findNavController().navigate(
                    TrainingCameraFragmentDirections.actionTrainingCameraFragmentToTrainingEndFragment(
                        args.trainingDay
                    )
                )
            } else {
                findNavController().navigate(
                    TrainingCameraFragmentDirections.actionTrainingCameraFragmentToPreparationTrainingFragment(
                        args.trainingDay,
                        args.exerciseId + 1
                    )
                )
            }
        }
        viewModel.processCameraProvider.observe(viewLifecycleOwner) { provider ->
            cameraProvider = provider
            bindAllCameraUseCases()
        }
    }

    private fun parseArgs() {
        isLastExercise = args.trainingDay.countOfExercise.size - 1 == args.exerciseId
        totalPushUps = args.trainingDay.countOfExercise[args.exerciseId]
    }


    private fun changeCamera() {
        if (cameraProvider == null) {
            return
        }
        val newLensFacing =
            if (lensFacing == CameraSelector.LENS_FACING_FRONT) {
                CameraSelector.LENS_FACING_BACK
            } else {
                CameraSelector.LENS_FACING_FRONT
            }
        val newCameraSelector = CameraSelector.Builder().requireLensFacing(newLensFacing).build()
        try {
            if (cameraProvider!!.hasCamera(newCameraSelector)) {
                lensFacing = newLensFacing
                cameraSelector = newCameraSelector
                bindAllCameraUseCases()
                return
            }
        } catch (e: CameraInfoUnavailableException) {
        }
    }

    override fun onResume() {
        super.onResume()
        bindAllCameraUseCases()
    }

    override fun onPause() {
        super.onPause()
        imageProcessor?.run { this.stop() }
    }


    private fun bindAllCameraUseCases() {
        if (cameraProvider != null) {
            cameraProvider!!.unbindAll()
            bindPreviewUseCase()
            bindAnalysisUseCase()
        }
    }

    private fun bindPreviewUseCase() {
        if (!PreferenceUtils.isCameraLiveViewportEnabled(requireContext())) {
            return
        }
        if (cameraProvider == null) {
            return
        }
        if (previewUseCase != null) {
            cameraProvider!!.unbind(previewUseCase)
        }

        val builder = Preview.Builder()
        val targetResolution =
            PreferenceUtils.getCameraXTargetResolution(requireContext(), lensFacing)
        if (targetResolution != null) {
            builder.setTargetResolution(targetResolution)
        }
        previewUseCase = builder.build()
        previewUseCase!!.setSurfaceProvider(binding.previewView.surfaceProvider)
        cameraProvider!!.bindToLifecycle(
            viewLifecycleOwner,
            cameraSelector!!,
            previewUseCase
        )
    }

    private fun bindAnalysisUseCase() {
        if (cameraProvider == null) {
            return
        }
        if (analysisUseCase != null) {
            cameraProvider!!.unbind(analysisUseCase)
        }
        if (imageProcessor != null) {
            imageProcessor!!.stop()
        }

        val poseDetectorOptions =
            PreferenceUtils.getPoseDetectorOptionsForLivePreview(requireContext())

        imageProcessor = PoseDetectorProcessor(
            requireContext(),
            poseDetectorOptions,
            true,
            true
        )
        imageProcessor!!.poseGraphicCreate = { poseGraphic ->
            binding.previewView.visibility = View.VISIBLE
            binding.graphicOverlay.visibility = View.VISIBLE
            binding.pushUpsProgress.visibility = View.VISIBLE
            binding.tvCount.visibility = View.VISIBLE
            binding.facingSwitch.visibility = View.VISIBLE
            binding.progressBar.hide()
            poseGraphic.onChangeValue = { countPushUps ->
                viewModel.updateCount(countPushUps)
            }
        }

        val builder = ImageAnalysis.Builder()
        val targetResolution =
            PreferenceUtils.getCameraXTargetResolution(requireContext(), lensFacing)
        if (targetResolution != null) {
            builder.setTargetResolution(targetResolution)
        }
        analysisUseCase = builder.build()

        needUpdateGraphicOverlayImageSourceInfo = true

        analysisUseCase?.setAnalyzer(
            ContextCompat.getMainExecutor(requireContext())
        ) { imageProxy: ImageProxy ->
            if (needUpdateGraphicOverlayImageSourceInfo) {
                val isImageFlipped = lensFacing == CameraSelector.LENS_FACING_FRONT
                val rotationDegrees = imageProxy.imageInfo.rotationDegrees
                if (rotationDegrees == 0 || rotationDegrees == 180) {
                    binding.graphicOverlay.setImageSourceInfo(
                        imageProxy.width,
                        imageProxy.height,
                        isImageFlipped
                    )
                } else {
                    binding.graphicOverlay.setImageSourceInfo(
                        imageProxy.height,
                        imageProxy.width,
                        isImageFlipped
                    )
                }
                needUpdateGraphicOverlayImageSourceInfo = false
            }
            try {
                imageProcessor!!.processImageProxy(imageProxy, binding.graphicOverlay)
            } catch (e: MlKitException) {
                Toast.makeText(requireActivity(), e.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        cameraProvider!!.bindToLifecycle(
            this,
            cameraSelector!!,
            analysisUseCase
        )
    }
}