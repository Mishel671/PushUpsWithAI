package ru.dzyubamichael.pushupswithai.domain

import javax.inject.Inject

class GetIsFirstStartUseCase @Inject constructor(
    private val repository: TrainingRepository
) {
    operator fun invoke() = repository.isFirstStart()
}