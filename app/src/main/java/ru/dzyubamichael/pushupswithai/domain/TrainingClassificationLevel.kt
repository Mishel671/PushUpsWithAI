package ru.dzyubamichael.pushupswithai.domain

data class TrainingClassificationLevel(
    val level: TrainingLevel,
    val trainingList: List<TrainingDayEntity>
)