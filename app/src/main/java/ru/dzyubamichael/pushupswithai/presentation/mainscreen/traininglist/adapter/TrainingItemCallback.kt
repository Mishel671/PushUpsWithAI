package ru.dzyubamichael.pushupswithai.presentation.mainscreen.traininglist.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.dzyubamichael.pushupswithai.domain.TrainingDayEntity

object TrainingItemCallback : DiffUtil.ItemCallback<TrainingDayEntity>() {

    override fun areItemsTheSame(oldItem: TrainingDayEntity, newItem: TrainingDayEntity): Boolean {
        return when {
            oldItem is TrainingDayEntity.Week &&
                    newItem is TrainingDayEntity.Week &&
                    oldItem.id == newItem.id -> true
            oldItem is TrainingDayEntity.TrainingDay &&
                    newItem is TrainingDayEntity.TrainingDay &&
                    oldItem.id == newItem.id -> true
            oldItem is TrainingDayEntity.RestDay &&
                    newItem is TrainingDayEntity.RestDay &&
                    oldItem.id == newItem.id -> true
            else -> false
        }
    }

    override fun areContentsTheSame(
        oldItem: TrainingDayEntity,
        newItem: TrainingDayEntity
    ): Boolean {
        return when {
            oldItem is TrainingDayEntity.Week &&
                    newItem is TrainingDayEntity.Week &&
                    oldItem == newItem -> true
            oldItem is TrainingDayEntity.TrainingDay &&
                    newItem is TrainingDayEntity.TrainingDay &&
                    oldItem == newItem -> true
            oldItem is TrainingDayEntity.RestDay &&
                    newItem is TrainingDayEntity.RestDay &&
                    oldItem == newItem -> true
            else -> false
        }
    }
}