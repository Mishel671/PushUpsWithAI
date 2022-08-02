package ru.dzyubamichael.pushupswithai.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.dzyubamichael.pushupswithai.domain.TrainingLevel

@Dao
interface TrainingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrainingData(trainingDbModel: TrainingDbModel)

    @Query("SELECT * FROM training_list WHERE trainingLevel=:level")
    fun getTrainingListByLevel(level: TrainingLevel): LiveData<List<TrainingDbModel>>
}