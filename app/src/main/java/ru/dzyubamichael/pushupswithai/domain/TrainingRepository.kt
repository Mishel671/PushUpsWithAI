package ru.dzyubamichael.pushupswithai.domain

import androidx.lifecycle.LiveData

interface TrainingRepository {

    suspend fun setFirstStart()

    fun isFirstStart():Boolean

    fun getTrainingListByLevel(trainingLevel: TrainingLevel):LiveData<List<TrainingDayEntity>>
}