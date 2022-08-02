package ru.dzyubamichael.pushupswithai.domain

import javax.inject.Inject

class GetTrainingByLevelListUseCase @Inject constructor(
    private val repository: TrainingRepository
) {
    operator fun invoke(level: TrainingLevel) = repository.getTrainingListByLevel(level)
}