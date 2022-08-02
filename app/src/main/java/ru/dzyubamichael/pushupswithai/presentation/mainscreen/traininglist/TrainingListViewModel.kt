package ru.dzyubamichael.pushupswithai.presentation.mainscreen.traininglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.dzyubamichael.pushupswithai.domain.GetTrainingByLevelListUseCase
import ru.dzyubamichael.pushupswithai.domain.TrainingDayEntity
import ru.dzyubamichael.pushupswithai.domain.TrainingLevel
import javax.inject.Inject

@HiltViewModel
class TrainingListViewModel @Inject constructor(
    private val getTrainingByLevelListUseCase: GetTrainingByLevelListUseCase
) : ViewModel() {

    fun getTrainingList(level: TrainingLevel):LiveData<List<TrainingDayEntity>>{
        return getTrainingByLevelListUseCase(level)
    }
}