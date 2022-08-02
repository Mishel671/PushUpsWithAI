package ru.dzyubamichael.pushupswithai.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.dzyubamichael.pushupswithai.domain.TrainingLevel

@Entity(tableName = "training_list")
@TypeConverters(ConverterCountOfExercise::class)
data class TrainingDbModel (

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val type: Type,
    @ColumnInfo(name = "trainingLevel")
    val trainingLevel: TrainingLevel,
    //Day Of Week params
    val countOfWeek: Int?,
    //Training day params
    val countOfExercise: List<Int>?,
    //Training day & rest day params
    val isPassed: Boolean?

) {
    enum class Type{
        WEEK,
        TRAINING_DAY,
        REST_DAY
    }
}