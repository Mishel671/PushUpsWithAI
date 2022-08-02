package ru.dzyubamichael.pushupswithai.data.staticdata

import ru.dzyubamichael.pushupswithai.domain.TrainingClassificationLevel
import ru.dzyubamichael.pushupswithai.domain.TrainingDayEntity
import ru.dzyubamichael.pushupswithai.domain.TrainingLevel

object TrainingDataInflate {

    fun getList(): List<TrainingClassificationLevel> {
        val listTraining = listOf(
            TrainingClassificationLevel(
                level = TrainingLevel.EASY,
                trainingList = listOf(
                    //Week one
                    TrainingDayEntity.Week(
                        countOfWeek = 1
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            2, 3, 2, 2, 3
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            3, 4, 2, 3, 4
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            4, 5, 4, 4, 5
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week two
                    TrainingDayEntity.Week(
                        countOfWeek = 2
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            4, 6, 4, 4, 6
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            5, 6, 4, 4, 7
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            5, 7, 5, 5, 8
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week three
                    TrainingDayEntity.Week(
                        countOfWeek = 3
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            10, 12, 7, 7, 9
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            10, 12, 8, 8, 12
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            11, 13, 9, 9, 13
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week four
                    TrainingDayEntity.Week(
                        countOfWeek = 4
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            12, 14, 11, 10, 16
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            14, 16, 12, 12, 18
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            16, 18, 13, 13, 20
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week five
                    TrainingDayEntity.Week(
                        countOfWeek = 5
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            17, 19, 15, 15, 20
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            10, 10, 13, 13, 10, 10, 9, 25
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            13, 13, 15, 15, 12, 12, 10, 30
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week six
                    TrainingDayEntity.Week(
                        countOfWeek = 6
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            25, 30, 20, 15, 40
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            14, 14, 15, 15, 14, 14, 10, 10, 44
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            13, 13, 17, 17, 16, 16, 14, 14, 50
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    )
                )
            ),

            TrainingClassificationLevel(
                level = TrainingLevel.MEDIUM,
                trainingList = listOf(
                    //Week one
                    TrainingDayEntity.Week(
                        countOfWeek = 1
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            6, 6, 4, 4, 5
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            6, 8, 6, 6, 7
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            8, 10, 7, 7, 10
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week two
                    TrainingDayEntity.Week(
                        countOfWeek = 2
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            9, 11, 8, 8, 11
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            10, 12, 9, 9, 13
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            12, 13, 10, 10, 15
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week three
                    TrainingDayEntity.Week(
                        countOfWeek = 3
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            12, 17, 13, 13, 17
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            14, 19, 14, 14, 19
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            16, 21, 15, 15, 21
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week four
                    TrainingDayEntity.Week(
                        countOfWeek = 4
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            18, 22, 16, 16, 25
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            20, 25, 20, 20, 28
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            23, 28, 23, 23, 33
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week five
                    TrainingDayEntity.Week(
                        countOfWeek = 5
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            28, 35, 25, 22, 35
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            18, 18, 20, 20, 14, 14, 16, 40
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            18, 18, 20, 20, 17, 17, 20, 45
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week six
                    TrainingDayEntity.Week(
                        countOfWeek = 6
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            40, 50, 25, 25, 50
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            20, 20, 23, 23, 20, 20, 18, 18, 53
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            22, 22, 30, 30, 25, 25, 18, 18, 55
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    )
                )
            ),

            TrainingClassificationLevel(
                level = TrainingLevel.HARD,
                trainingList = listOf(
                    //Week one
                    TrainingDayEntity.Week(
                        countOfWeek = 1
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            10, 12, 7, 7, 9
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            10, 12, 8, 8, 12
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            11, 15, 9, 9, 13
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week two
                    TrainingDayEntity.Week(
                        countOfWeek = 2
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            14, 14, 10, 10, 15
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            14, 16, 12, 12, 17
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            16, 17, 14, 14, 20
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week three
                    TrainingDayEntity.Week(
                        countOfWeek = 3
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            14, 18, 14, 14, 20
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            20, 25, 15, 15, 25
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            22, 30, 20, 20, 28
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week four
                    TrainingDayEntity.Week(
                        countOfWeek = 4
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            21, 25, 21, 21, 32
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            25, 29, 25, 25, 36
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            29, 33, 29, 29, 40
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week five
                    TrainingDayEntity.Week(
                        countOfWeek = 5
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            36, 40, 30, 24, 40
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            19, 19, 22, 22, 18, 18, 22, 45
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            20, 20, 24, 24, 20, 20, 22, 50
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),

                    //Week six
                    TrainingDayEntity.Week(
                        countOfWeek = 6
                    ),
                    //Day one
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            45, 55, 35, 30, 55
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day two
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            22, 22, 30, 30, 24, 24, 18, 18, 58
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    ),
                    //Day three
                    TrainingDayEntity.TrainingDay(
                        countOfExercise = listOf(
                            26, 26, 33, 33, 26, 26, 22, 22, 60
                        ),
                        isPassed = false
                    ),
                    TrainingDayEntity.RestDay(
                        isPassed = false
                    )
                )
            )
        )
        return listTraining
    }

}