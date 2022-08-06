package ru.dzyubamichael.pushupswithai.data

import ru.dzyubamichael.pushupswithai.data.database.TrainingDbModel
import ru.dzyubamichael.pushupswithai.domain.TrainingDayEntity
import ru.dzyubamichael.pushupswithai.domain.TrainingLevel

fun TrainingDayEntity.mapToDbModel(level: TrainingLevel): TrainingDbModel {
    val entity = this
    when (entity) {
        is TrainingDayEntity.Week -> {
            return TrainingDbModel(
                id = entity.id,
                type = TrainingDbModel.Type.WEEK,
                trainingLevel = level,
                countOfWeek = entity.countOfWeek,
                countOfExercise = null,
                isPassed = null,
                day = null
            )
        }
        is TrainingDayEntity.TrainingDay -> {
            return TrainingDbModel(
                id = entity.id,
                type = TrainingDbModel.Type.TRAINING_DAY,
                trainingLevel = level,
                countOfExercise = entity.countOfExercise,
                isPassed = entity.isPassed,
                day = entity.day,
                countOfWeek = null
            )
        }
        is TrainingDayEntity.RestDay -> {
            return TrainingDbModel(
                id = entity.id,
                type = TrainingDbModel.Type.REST_DAY,
                isPassed = entity.isPassed,
                trainingLevel = level,
                day = entity.day,
                countOfExercise = null,
                countOfWeek = null
            )
        }
    }
}

fun TrainingDbModel.mapToEntity(): TrainingDayEntity {
    val entity = this
    when (entity.type) {
        TrainingDbModel.Type.WEEK -> {
            return TrainingDayEntity.Week(
                id = entity.id,
                countOfWeek = entity.countOfWeek!!
            )
        }
        TrainingDbModel.Type.TRAINING_DAY -> {
            return TrainingDayEntity.TrainingDay(
                id = entity.id,
                countOfExercise = entity.countOfExercise!!,
                day = entity.day!!,
                isPassed = entity.isPassed!!
            )
        }
        TrainingDbModel.Type.REST_DAY -> {
            return TrainingDayEntity.RestDay(
                id = entity.id,
                day = entity.day!!,
                isPassed = entity.isPassed!!
            )
        }
    }
}