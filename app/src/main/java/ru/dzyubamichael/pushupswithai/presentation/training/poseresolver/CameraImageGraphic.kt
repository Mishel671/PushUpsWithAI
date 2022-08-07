package ru.dzyubamichael.pushupswithai.presentation.training.poseresolver

import android.graphics.Bitmap
import android.graphics.Canvas
import ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.GraphicOverlay.Graphic

class CameraImageGraphic(overlay: GraphicOverlay?, private val bitmap: Bitmap) : Graphic(
    overlay!!
) {
    override fun draw(canvas: Canvas?) {
        canvas!!.drawBitmap(bitmap, getTransformationMatrix(), null)
    }
}