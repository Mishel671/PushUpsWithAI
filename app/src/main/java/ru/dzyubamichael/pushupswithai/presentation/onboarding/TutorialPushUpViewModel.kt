package ru.dzyubamichael.pushupswithai.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.dzyubamichael.pushupswithai.domain.SaveFirstStartUseCase
import javax.inject.Inject

@HiltViewModel
class TutorialPushUpViewModel @Inject constructor(
    private val saveFirstStartUseCase: SaveFirstStartUseCase
): ViewModel() {

    fun saveFirstStart(){
        viewModelScope.launch {
            saveFirstStartUseCase()
        }
    }
}