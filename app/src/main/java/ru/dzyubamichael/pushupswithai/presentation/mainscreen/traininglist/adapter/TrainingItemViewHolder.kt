package ru.dzyubamichael.pushupswithai.presentation.mainscreen.traininglist.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.dzyubamichael.pushupswithai.databinding.*

sealed class TrainingItemViewHolder(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    class WeekViewHolder(val binding: WeekItemBinding) : TrainingItemViewHolder(binding){

    }

    class TrainingDayViewHolder(val binding: TrainingItemBinding) : TrainingItemViewHolder(binding){

    }

    class RestViewHolder(val binding: RestItemBinding) : TrainingItemViewHolder(binding){

    }

    class TrainingDayActiveViewHolder(val binding: TrainingItemActiveBinding) : TrainingItemViewHolder(binding){

    }

    class RestViewActiveViewHolder(val binding: RestItemActiveBinding) : TrainingItemViewHolder(binding){

    }

    class PassedViewHolder(val binding: PassedItemBinding) : TrainingItemViewHolder(binding){

    }
}