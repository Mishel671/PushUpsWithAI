package ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.preference

import android.content.Context
import android.preference.PreferenceManager
import android.util.Size
import androidx.camera.core.CameraSelector
import com.google.common.base.Preconditions
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions
import ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.CameraSource
import ru.dzyubamichael.pushupswithai.presentation.training.poseresolver.CameraSource.SizePair

object PreferenceUtils {
    private const val POSE_DETECTOR_PERFORMANCE_MODE_FAST = 1

    private const val PREF_KEY_REAR_CAMERA_PREVIEW_SIZE = "rcpvs"
    private const val PREF_KEY_REAR_CAMERA_PICTURE_SIZE = "rcpts"
    private const val PREF_KEY_FRONT_CAMERA_PREVIEW_SIZE = "fcpvs"
    private const val PREF_KEY_FRONT_CAMERA_PICTURE_SIZE = "fcpts"
    private const val PREF_KEY_CAMERAX_REAR_CAMERA_TARGET_RESOLUTION = "crctas"
    private const val PREF_KEY_CAMERAX_FRONT_CAMERA_TARGET_RESOLUTION = "cfctas"
    private const val PREF_KEY_INFO_HIDE = "ih"
    private const val PREF_KEY_LIVE_PREVIEW_POSE_DETECTION_PERFORMANCE_MODE = "lppdpm"
    private const val PREF_KEY_POSE_DETECTOR_PREFER_GPU = "pdpg"
    private const val PREF_KEY_CAMERA_LIVE_VIEWPORT = "clv"


    @JvmStatic
    fun getCameraPreviewSizePair(context: Context, cameraId: Int): SizePair? {
        Preconditions.checkArgument(
            cameraId == CameraSource.CAMERA_FACING_BACK
                    || cameraId == CameraSource.CAMERA_FACING_FRONT
        )
        val previewSizePrefKey: String
        val pictureSizePrefKey: String
        if (cameraId == CameraSource.CAMERA_FACING_BACK) {
            previewSizePrefKey = PREF_KEY_REAR_CAMERA_PREVIEW_SIZE
            pictureSizePrefKey = PREF_KEY_REAR_CAMERA_PICTURE_SIZE
        } else {
            previewSizePrefKey = PREF_KEY_FRONT_CAMERA_PREVIEW_SIZE
            pictureSizePrefKey = PREF_KEY_FRONT_CAMERA_PICTURE_SIZE
        }
        return try {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            SizePair(
                com.google.android.gms.common.images.Size.parseSize(
                    sharedPreferences.getString(
                        previewSizePrefKey,
                        null
                    )!!
                ),
                com.google.android.gms.common.images.Size.parseSize(
                    sharedPreferences.getString(
                        pictureSizePrefKey,
                        null
                    )!!
                )
            )
        } catch (e: Exception) {
            null
        }
    }

    fun getCameraXTargetResolution(context: Context, lensfacing: Int): Size? {
        Preconditions.checkArgument(
            lensfacing == CameraSelector.LENS_FACING_BACK
                    || lensfacing == CameraSelector.LENS_FACING_FRONT
        )
        val prefKey =
            if (lensfacing == CameraSelector.LENS_FACING_BACK)
                PREF_KEY_CAMERAX_REAR_CAMERA_TARGET_RESOLUTION
            else
                PREF_KEY_CAMERAX_FRONT_CAMERA_TARGET_RESOLUTION
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return try {
            Size.parseSize(sharedPreferences.getString(prefKey, null))
        } catch (e: Exception) {
            null
        }
    }

    fun shouldHideDetectionInfo(context: Context): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefKey = PREF_KEY_INFO_HIDE
        return sharedPreferences.getBoolean(prefKey, false)
    }

    fun getPoseDetectorOptionsForLivePreview(context: Context): PoseDetectorOptionsBase {
        val performanceMode = getModeTypePreferenceValue(
            context,
            PREF_KEY_LIVE_PREVIEW_POSE_DETECTION_PERFORMANCE_MODE,
            POSE_DETECTOR_PERFORMANCE_MODE_FAST
        )
        val preferGPU = preferGPUForPoseDetection(context)
        return if (performanceMode == POSE_DETECTOR_PERFORMANCE_MODE_FAST) {
            val builder =
                PoseDetectorOptions.Builder().setDetectorMode(PoseDetectorOptions.STREAM_MODE)
            if (preferGPU) {
                builder.setPreferredHardwareConfigs(PoseDetectorOptions.CPU_GPU)
            }
            builder.build()
        } else {
            val builder = AccuratePoseDetectorOptions.Builder()
                .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
            if (preferGPU) {
                builder.setPreferredHardwareConfigs(AccuratePoseDetectorOptions.CPU_GPU)
            }
            builder.build()
        }
    }

    fun preferGPUForPoseDetection(context: Context): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefKey = PREF_KEY_POSE_DETECTOR_PREFER_GPU
        return sharedPreferences.getBoolean(prefKey, true)
    }

    private fun getModeTypePreferenceValue(
        context: Context, prefKeyStr: String, defaultValue: Int
    ): Int {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefKey = prefKeyStr
        return sharedPreferences.getString(prefKey, defaultValue.toString())!!.toInt()
    }

    fun isCameraLiveViewportEnabled(context: Context): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefKey = PREF_KEY_CAMERA_LIVE_VIEWPORT
        return sharedPreferences.getBoolean(prefKey, false)
    }

}