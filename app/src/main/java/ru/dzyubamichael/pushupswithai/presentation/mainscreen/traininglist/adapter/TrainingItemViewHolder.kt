package ru.dzyubamichael.pushupswithai.presentation.mainscreen.traininglist.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.dzyubamichael.pushupswithai.databinding.RestItemBinding
import ru.dzyubamichael.pushupswithai.databinding.TrainingItemBinding
import ru.dzyubamichael.pushupswithai.databinding.WeekItemBinding

sealed class TrainingItemViewHolder(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    class WeekViewHolder(val binding: WeekItemBinding) : TrainingItemViewHolder(binding){

    }

    class TrainingDayViewHolder(val binding: TrainingItemBinding) : TrainingItemViewHolder(binding){

    }

    class RestViewHolder(val binding: RestItemBinding) : TrainingItemViewHolder(binding){

    }
}