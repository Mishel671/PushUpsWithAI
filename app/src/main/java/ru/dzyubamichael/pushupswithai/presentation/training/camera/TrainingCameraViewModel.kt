package ru.dzyubamichael.pushupswithai.presentation.training.camera

import android.app.Application
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutionException
import javax.inject.Inject

@HiltViewModel
class TrainingCameraViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {
    private var cameraProviderLiveData: MutableLiveData<ProcessCameraProvider>? = null

    private val _countPushUps = MutableLiveData<Int>()
    val countPushUps: LiveData<Int>
        get() = _countPushUps

    private val _exerciseEnd = MutableLiveData<Unit>()
    val exerciseEnd: LiveData<Unit>
        get() = _exerciseEnd

    private var totalPushUps = 0

    init {
        _countPushUps.value = 0
    }

    fun setTotalPushUps(value: Int) {
        totalPushUps = value
    }

    fun updateCount(countPushUps: Int) {
        if (countPushUps > _countPushUps.value!!.toInt()) {
            if (countPushUps == totalPushUps) {
                viewModelScope.launch {
                    delay(1500)
                    _exerciseEnd.value = Unit
                }
            }
            _countPushUps.value = countPushUps
        }
    }

    val processCameraProvider: LiveData<ProcessCameraProvider>
        get() {
            if (cameraProviderLiveData == null) {
                cameraProviderLiveData = MutableLiveData()
                val cameraProviderFuture = ProcessCameraProvider.getInstance(application)
                cameraProviderFuture.addListener(
                    {
                        try {
                            cameraProviderLiveData!!.setValue(cameraProviderFuture.get())
                        } catch (e: ExecutionException) {
                        } catch (e: InterruptedException) {
                        }
                    },
                    ContextCompat.getMainExecutor(application)
                )
            }
            return cameraProviderLiveData!!
        }
}