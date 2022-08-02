package ru.dzyubamichael.pushupswithai.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.dzyubamichael.pushupswithai.data.TrainingRepositoryImpl
import ru.dzyubamichael.pushupswithai.data.database.AppDatabase
import ru.dzyubamichael.pushupswithai.data.database.TrainingDao
import ru.dzyubamichael.pushupswithai.domain.TrainingRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindGameRepository(impl: TrainingRepositoryImpl): TrainingRepository

    companion object {
        @Singleton
        @Provides
        fun provideTrainingDao(
            application: Application
        ): TrainingDao {
            return AppDatabase.getInstance(application).trainingDao()
        }
    }
}