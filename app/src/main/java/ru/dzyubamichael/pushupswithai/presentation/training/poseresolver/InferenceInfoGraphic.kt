package ru.dzyubamichael.pushupswithai.presentation.training.poseresolver

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.GraphicOverlay.Graphic

class InferenceInfoGraphic(
    private val overlay: GraphicOverlay,
    private val frameLatency: Long,
    private val detectorLatency: Long,
    private val framesPerSecond: Int?
) : Graphic(overlay) {
    private val textPaint: Paint
    private var showLatencyInfo = true

    constructor(overlay: GraphicOverlay) : this(overlay, 0, 0, null) {
        showLatencyInfo = false
    }

    @Synchronized
    override fun draw(canvas: Canvas?) {

    }

    companion object {
        private const val TEXT_COLOR = Color.WHITE
        private const val TEXT_SIZE = 60.0f
    }

    init {
        textPaint = Paint()
        textPaint.color = TEXT_COLOR
        textPaint.textSize = TEXT_SIZE
        textPaint.setShadowLayer(5.0f, 0f, 0f, Color.BLACK)
        postInvalidate()
    }
}