package ru.dzyubamichael.pushupswithai.presentation.restscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dzyubamichael.pushupswithai.domain.SetPassedItemUseCase
import javax.inject.Inject

@HiltViewModel
class RestDayViewModel @Inject constructor(
    private val setPassedItemUseCase: SetPassedItemUseCase
) : ViewModel() {

    fun setPassed(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            setPassedItemUseCase(id)
        }
    }
}