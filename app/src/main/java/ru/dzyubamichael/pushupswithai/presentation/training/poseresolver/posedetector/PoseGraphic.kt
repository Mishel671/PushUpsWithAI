package ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.posedetector

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.google.common.primitives.Ints
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark
import ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.GraphicOverlay
import ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.GraphicOverlay.Graphic
import java.lang.Math.max
import java.lang.Math.min

class PoseGraphic internal constructor(
    overlay: GraphicOverlay,
    private val pose: Pose,
    private val visualizeZ: Boolean,
    private val rescaleZForVisualization: Boolean,
    private val poseClassification: List<Int?>
) : Graphic(overlay) {

    var onChangeValue: ((Int) -> Unit)? = null

    private var zMin = java.lang.Float.MAX_VALUE
    private var zMax = java.lang.Float.MIN_VALUE

    private val linePaint = Paint().apply {
        strokeWidth = STROKE_WIDTH
        color = Color.parseColor("#3FBCED")

    }

    override fun draw(canvas: Canvas?) {
        val landmarks = pose.allPoseLandmarks
        if (landmarks.isEmpty()) {
            return
        }

        for (i in poseClassification.indices) {
            onChangeValue?.invoke(poseClassification[i] ?: 0)
        }


        val leftShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)
        val rightShoulder = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)
        val leftElbow = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW)
        val rightElbow = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)
        val leftWrist = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST)
        val rightWrist = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST)
        val leftHip = pose.getPoseLandmark(PoseLandmark.LEFT_HIP)
        val rightHip = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP)
        val leftKnee = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE)
        val rightKnee = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE)
        val leftAnkle = pose.getPoseLandmark(PoseLandmark.LEFT_ANKLE)
        val rightAnkle = pose.getPoseLandmark(PoseLandmark.RIGHT_ANKLE)

        val leftPinky = pose.getPoseLandmark(PoseLandmark.LEFT_PINKY)
        val rightPinky = pose.getPoseLandmark(PoseLandmark.RIGHT_PINKY)
        val leftIndex = pose.getPoseLandmark(PoseLandmark.LEFT_INDEX)
        val rightIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_INDEX)
        val leftThumb = pose.getPoseLandmark(PoseLandmark.LEFT_THUMB)
        val rightThumb = pose.getPoseLandmark(PoseLandmark.RIGHT_THUMB)
        val leftHeel = pose.getPoseLandmark(PoseLandmark.LEFT_HEEL)
        val rightHeel = pose.getPoseLandmark(PoseLandmark.RIGHT_HEEL)
        val leftFootIndex = pose.getPoseLandmark(PoseLandmark.LEFT_FOOT_INDEX)
        val rightFootIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_FOOT_INDEX)

        //Shoulders
        drawLine(canvas!!, leftShoulder, rightShoulder, linePaint)
        drawLine(canvas, leftHip, rightHip, linePaint)

        // Left body
        drawLine(canvas, leftShoulder, leftElbow, linePaint)
        drawLine(canvas, leftElbow, leftWrist, linePaint)
        drawLine(canvas, leftShoulder, leftHip, linePaint)
        drawLine(canvas, leftHip, leftKnee, linePaint)
        drawLine(canvas, leftKnee, leftAnkle, linePaint)
        drawLine(canvas, leftWrist, leftThumb, linePaint)
        drawLine(canvas, leftWrist, leftPinky, linePaint)
        drawLine(canvas, leftWrist, leftIndex, linePaint)
        drawLine(canvas, leftIndex, leftPinky, linePaint)
        drawLine(canvas, leftAnkle, leftHeel, linePaint)
        drawLine(canvas, leftHeel, leftFootIndex, linePaint)

        // Right body
        drawLine(canvas, rightShoulder, rightElbow, linePaint)
        drawLine(canvas, rightElbow, rightWrist, linePaint)
        drawLine(canvas, rightShoulder, rightHip, linePaint)
        drawLine(canvas, rightHip, rightKnee, linePaint)
        drawLine(canvas, rightKnee, rightAnkle, linePaint)
        drawLine(canvas, rightWrist, rightThumb, linePaint)
        drawLine(canvas, rightWrist, rightPinky, linePaint)
        drawLine(canvas, rightWrist, rightIndex, linePaint)
        drawLine(canvas, rightIndex, rightPinky, linePaint)
        drawLine(canvas, rightAnkle, rightHeel, linePaint)
        drawLine(canvas, rightHeel, rightFootIndex, linePaint)

    }

    internal fun drawPoint(canvas: Canvas, landmark: PoseLandmark, paint: Paint) {
        val point = landmark.position3D
        maybeUpdatePaintColor(paint, canvas, point.z)
        canvas.drawCircle(translateX(point.x), translateY(point.y), DOT_RADIUS, paint)
    }

    internal fun drawLine(
        canvas: Canvas,
        startLandmark: PoseLandmark?,
        endLandmark: PoseLandmark?,
        paint: Paint
    ) {
        val start = startLandmark!!.position3D
        val end = endLandmark!!.position3D

        canvas.drawLine(
            translateX(start.x),
            translateY(start.y),
            translateX(end.x),
            translateY(end.y),
            linePaint
        )
    }

    internal fun maybeUpdatePaintColor(
        paint: Paint,
        canvas: Canvas,
        zInImagePixel: Float
    ) {
        if (!visualizeZ) {
            return
        }

        val zLowerBoundInScreenPixel: Float
        val zUpperBoundInScreenPixel: Float

        if (rescaleZForVisualization) {
            zLowerBoundInScreenPixel = min(-0.001f, scale(zMin))
            zUpperBoundInScreenPixel = max(0.001f, scale(zMax))
        } else {
            val defaultRangeFactor = 1f
            zLowerBoundInScreenPixel = -defaultRangeFactor * canvas.width
            zUpperBoundInScreenPixel = defaultRangeFactor * canvas.width
        }

        val zInScreenPixel = scale(zInImagePixel)

        if (zInScreenPixel < 0) {
            var v = (zInScreenPixel / zLowerBoundInScreenPixel * 255).toInt()
            v = Ints.constrainToRange(v, 0, 255)
            paint.setARGB(255, 255, 255 - v, 255 - v)
        } else {
            var v = (zInScreenPixel / zUpperBoundInScreenPixel * 255).toInt()
            v = Ints.constrainToRange(v, 0, 255)
            paint.setARGB(255, 255 - v, 255 - v, 255)
        }
    }

    companion object {

        private val DOT_RADIUS = 8.0f
        private val STROKE_WIDTH = 1.0f
    }
}
