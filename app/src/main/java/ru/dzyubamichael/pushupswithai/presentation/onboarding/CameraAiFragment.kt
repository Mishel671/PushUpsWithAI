package ru.dzyubamichael.pushupswithai.presentation.onboarding

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.FragmentCameraAiBinding
import ru.dzyubamichael.pushupswithai.presentation.viewBinding

class CameraAiFragment : Fragment(R.layout.fragment_camera_ai) {

    private val binding by viewBinding(FragmentCameraAiBinding::bind)

    private val activityResultLauncher: ActivityResultLauncher<Array<String>>

    init {
        activityResultLauncher = createLauncher()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isGranted = ContextCompat.checkSelfPermission(
            requireActivity(), Manifest.permission.CAMERA
        ) != PackageManager.PERMISSION_DENIED
        if (isGranted) {
            setButtonNext()
        } else {
            lifecycleScope.launch {
                delay(3000)
                val appPerms = arrayOf(Manifest.permission.CAMERA)
                activityResultLauncher.launch(appPerms)
            }
        }

    }

    fun createLauncher(): ActivityResultLauncher<Array<String>> {
        return registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            var allAreGranted = true
            for (b in result.values) {
                allAreGranted = allAreGranted && b
            }
            if (allAreGranted) {
                setButtonNext()
            } else {
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.grant_permission),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setButtonNext() {
        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_cameraAiFragment_to_tutorialPushUpFragment)
        }
    }
}