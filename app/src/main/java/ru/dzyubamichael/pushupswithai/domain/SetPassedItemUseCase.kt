package ru.dzyubamichael.pushupswithai.domain

import javax.inject.Inject

class SetPassedItemUseCase @Inject constructor(
    private val repository: TrainingRepository
) {
    suspend operator fun invoke(id: Int) = repository.setPassedDay(id)
}