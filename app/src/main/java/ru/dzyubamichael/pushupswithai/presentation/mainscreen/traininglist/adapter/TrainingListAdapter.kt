package ru.dzyubamichael.pushupswithai.presentation.mainscreen.traininglist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import ru.dzyubamichael.pushupswithai.databinding.RestItemBinding
import ru.dzyubamichael.pushupswithai.databinding.TrainingItemBinding
import ru.dzyubamichael.pushupswithai.databinding.WeekItemBinding
import ru.dzyubamichael.pushupswithai.domain.TrainingDayEntity

class TrainingListAdapter(
    private val context: Context
) : ListAdapter<TrainingDayEntity, TrainingItemViewHolder>(TrainingItemCallback) {

    var onTrainingClickListener: ((TrainingDayEntity.TrainingDay) -> Unit)? = null
    var onRestClickListener: ((TrainingDayEntity.RestDay) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingItemViewHolder {
        return when (viewType) {
            VIEW_TYPE_WEEK -> {
                TrainingItemViewHolder.WeekViewHolder(
                    WeekItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_TRAINING -> {
                TrainingItemViewHolder.TrainingDayViewHolder(
                    TrainingItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_REST -> {
                TrainingItemViewHolder.RestViewHolder(
                    RestItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: TrainingItemViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is TrainingItemViewHolder.WeekViewHolder -> {
                item as TrainingDayEntity.Week
                val binding = holder.binding
                binding.tvWeek.text = "Неделя" + " ${item.countOfWeek}"
            }
            is TrainingItemViewHolder.TrainingDayViewHolder -> {
                item as TrainingDayEntity.TrainingDay
                val binding = holder.binding
                binding.root.setOnClickListener {
                    onTrainingClickListener?.invoke(item)
                }
            }
            is TrainingItemViewHolder.RestViewHolder -> {
                item as TrainingDayEntity.RestDay
                val binding = holder.binding
                binding.root.setOnClickListener {
                    onRestClickListener?.invoke(item)
                }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is TrainingDayEntity.Week -> {
                VIEW_TYPE_WEEK
            }
            is TrainingDayEntity.TrainingDay -> {
                VIEW_TYPE_TRAINING
            }
            is TrainingDayEntity.RestDay -> {
                VIEW_TYPE_REST
            }
        }
    }

    companion object {
        const val VIEW_TYPE_WEEK = 100
        const val VIEW_TYPE_TRAINING = 101
        const val VIEW_TYPE_REST = 102

        const val MAX_POOL_SIZE_WEEK = 3
        const val MAX_POOL_SIZE_TRAINING = 10
        const val MAX_POOL_SIZE_REST = 10
    }
}