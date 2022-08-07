package ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.posedetector

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.android.odml.image.MlImage
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetector
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase
import ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.GraphicOverlay
import ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.VisionProcessorBase
import ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.posedetector.classification.PoseClassifierProcessor
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class PoseDetectorProcessor(
    private val context: Context,
    options: PoseDetectorOptionsBase,
    private val runClassification: Boolean,
    private val isStreamMode: Boolean
) : VisionProcessorBase<PoseDetectorProcessor.PoseWithClassification>(context) {

    private val detector: PoseDetector
    private val classificationExecutor: Executor
    private var poseClassifierProcessor: PoseClassifierProcessor? = null

    class PoseWithClassification(val pose: Pose, val classificationResult: List<Int?>)

    var poseGraphicCreate: ((PoseGraphic) -> Unit)? = null

    init {
        detector = PoseDetection.getClient(options)
        classificationExecutor = Executors.newSingleThreadExecutor()
    }

    override fun stop() {
        super.stop()
        detector.close()
    }

    override fun detectInImage(image: InputImage): Task<PoseWithClassification> {
        return detector
            .process(image)
            .continueWith(
                classificationExecutor
            ) { task ->
                val pose = task.result
                var classificationResult: List<Int?> = ArrayList()
                if (runClassification) {
                    if (poseClassifierProcessor == null) {
                        poseClassifierProcessor = PoseClassifierProcessor(context, isStreamMode)
                    }
                    classificationResult = poseClassifierProcessor!!.getPoseResult(pose)
                }
                PoseWithClassification(pose, classificationResult)
            }
    }

    override fun detectInImage(image: MlImage): Task<PoseWithClassification> {
        return detector
            .process(image)
            .continueWith(
                classificationExecutor
            ) { task ->
                val pose = task.result
                var classificationResult: List<Int?> = ArrayList()
                if (runClassification) {
                    if (poseClassifierProcessor == null) {
                        poseClassifierProcessor = PoseClassifierProcessor(context, isStreamMode)
                    }
                    classificationResult = poseClassifierProcessor!!.getPoseResult(pose)
                }
                PoseWithClassification(pose, classificationResult)
            }
    }

    override fun onSuccess(
        poseWithClassification: PoseWithClassification,
        graphicOverlay: GraphicOverlay
    ) {
        val poseGraphic = PoseGraphic(
            graphicOverlay,
            poseWithClassification.pose,
            true,
            false,
            poseWithClassification.classificationResult
        )
        graphicOverlay.add(poseGraphic)
        poseGraphicCreate?.invoke(poseGraphic)
    }

    override fun onFailure(e: Exception) {
    }

    override fun isMlImageEnabled(context: Context?): Boolean {
        return true
    }

}
