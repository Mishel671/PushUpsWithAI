package ru.dzyubamichael.pushupswithai.domain

import javax.inject.Inject

class SaveFirstStartUseCase @Inject constructor(
    private val repository: TrainingRepository
) {
    suspend operator fun invoke() = repository.setFirstStart()
}