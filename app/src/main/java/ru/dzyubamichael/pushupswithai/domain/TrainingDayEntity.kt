package ru.dzyubamichael.pushupswithai.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


sealed class TrainingDayEntity {

    data class Week(
        val countOfWeek: Int,
        var id: Int = UNDEFINED_ID
    ) : TrainingDayEntity()

    @Parcelize
    data class TrainingDay(
        val day: Int,
        val countOfExercise: List<Int>,
        val isPassed: Boolean,
        var id: Int = UNDEFINED_ID
    ) : TrainingDayEntity(), Parcelable

    @Parcelize
    data class RestDay(
        val day: Int,
        val isPassed: Boolean,
        var id: Int = UNDEFINED_ID
    ) : TrainingDayEntity(), Parcelable

    companion object {
        const val UNDEFINED_ID = 0
    }
}