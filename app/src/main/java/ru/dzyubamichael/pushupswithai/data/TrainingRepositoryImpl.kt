package ru.dzyubamichael.pushupswithai.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ru.dzyubamichael.pushupswithai.data.database.TrainingDao
import ru.dzyubamichael.pushupswithai.data.preferences.AppPreferences
import ru.dzyubamichael.pushupswithai.data.staticdata.TrainingDataInflate
import ru.dzyubamichael.pushupswithai.domain.TrainingDayEntity
import ru.dzyubamichael.pushupswithai.domain.TrainingLevel
import ru.dzyubamichael.pushupswithai.domain.TrainingRepository
import javax.inject.Inject

class TrainingRepositoryImpl @Inject constructor(
    private val trainingDao: TrainingDao
) : TrainingRepository {

    override suspend fun setFirstStart() {
        val listTraining = TrainingDataInflate.getList()
        for (trainingsWithLevel in listTraining) {
            for (trainingEntity in trainingsWithLevel.trainingList) {
                val trainingDbModel = trainingEntity.mapToDbModel(trainingsWithLevel.level)
                trainingDao.insertTrainingData(trainingDbModel)
            }
        }
        AppPreferences.saveFirstStart()
    }

    override fun getTrainingListByLevel(trainingLevel: TrainingLevel): LiveData<List<TrainingDayEntity>> {
        val listDbModelLiveData = trainingDao.getTrainingListByLevel(trainingLevel)
        return Transformations.map(listDbModelLiveData){ listDbModel ->
            listDbModel.map { dbModel ->
                dbModel.mapToEntity()
            }
        }
    }

    override fun isFirstStart(): Boolean {
        return AppPreferences.isFirstStart()
    }

}