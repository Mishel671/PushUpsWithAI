package ru.dzyubamichael.pushupswithai.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.dzyubamichael.pushupswithai.domain.GetIsFirstStartUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getIsFirstStartUseCase: GetIsFirstStartUseCase
) : ViewModel() {

    private val _launchNextScreen = MutableLiveData<Unit>()
    val launchNextScreen: LiveData<Unit>
        get() = _launchNextScreen

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                delay(2000)
            }
            _launchNextScreen.value = Unit
        }
    }

    fun isFirstStart(): Boolean {
        return getIsFirstStartUseCase()
    }
}