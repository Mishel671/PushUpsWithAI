package ru.dzyubamichael.pushupswithai.presentation.training.start

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.ExerciseItemBinding

class ExercisesAdapter(
    private val context: Context,
    private val exercisesList: List<Int>
) : RecyclerView.Adapter<ExerciseItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseItemViewHolder {
        val binding = ExerciseItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExerciseItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseItemViewHolder, position: Int) {
        val exerciseCount = exercisesList[position]
        val binding = holder.binding
        binding.tvIncrementer.text = String.format(
            context.getString(R.string.incrementer_pattern),
            position + 1
        )
        binding.tvCountExercise.text = String.format(
            context.getString(R.string.count_pattern),
            exerciseCount
        )

    }

    override fun getItemCount(): Int {
        return exercisesList.size
    }
}