package ru.dzyubamichael.pushupswithai.presentation.mainscreen.traininglist.adapter

import android.animation.ObjectAnimator
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.*
import ru.dzyubamichael.pushupswithai.domain.TrainingDayEntity

class TrainingListAdapter(
    private val context: Context
) : ListAdapter<TrainingDayEntity, TrainingItemViewHolder>(TrainingItemCallback) {

    var onTrainingClickListener: ((TrainingDayEntity.TrainingDay) -> Unit)? = null
    var onRestClickListener: ((TrainingDayEntity.RestDay) -> Unit)? = null

    private var activeItem = 0

    private var previousExpandItem = -1

    fun updateList(list: List<TrainingDayEntity>, lastActive: Int) {
        activeItem = lastActive
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingItemViewHolder {
        return when (viewType) {
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
            VIEW_TYPE_PASSED -> {
                TrainingItemViewHolder.PassedViewHolder(
                    PassedItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_WEEK -> {
                TrainingItemViewHolder.WeekViewHolder(
                    WeekItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_TRAINING_ACTIVE -> {
                TrainingItemViewHolder.TrainingDayActiveViewHolder(
                    TrainingItemActiveBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_REST_ACTIVE -> {
                TrainingItemViewHolder.RestViewActiveViewHolder(
                    RestItemActiveBinding.inflate(
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
        Log.d("ListLog", "List position: $position, Active element: $activeItem")
        when (holder) {
            is TrainingItemViewHolder.WeekViewHolder -> {
                setWeekItem(holder.binding, item as TrainingDayEntity.Week)
            }
            is TrainingItemViewHolder.TrainingDayViewHolder -> {
                setTrainingItem(holder.binding, item as TrainingDayEntity.TrainingDay, position)
            }
            is TrainingItemViewHolder.RestViewHolder -> {
                setRestItem(holder.binding, item as TrainingDayEntity.RestDay)
            }
            is TrainingItemViewHolder.PassedViewHolder -> {
                setPassedItem(holder.binding, item)
            }
            is TrainingItemViewHolder.TrainingDayActiveViewHolder -> {
                setTrainingActiveItem(
                    holder.binding,
                    item as TrainingDayEntity.TrainingDay,
                    position
                )
            }
            is TrainingItemViewHolder.RestViewActiveViewHolder -> {
                setRestActiveItem(holder.binding, item as TrainingDayEntity.RestDay)
            }
        }
    }

    private fun setWeekItem(binding: WeekItemBinding, item: TrainingDayEntity.Week) {
        binding.tvWeek.text = String.format(
            context.getString(R.string.week_title),
            item.countOfWeek
        )
    }

    private fun setTrainingItem(
        binding: TrainingItemBinding,
        item: TrainingDayEntity.TrainingDay,
        position: Int
    ) {
        binding.tvDay.text = String.format(
            context.getString(R.string.day_title),
            item.day
        )
        var description = ""
        val pattern = context.getString(R.string.training_description)
        for (i in 0 until item.countOfExercise.size) {
            description += String.format(pattern, i + 1, item.countOfExercise[i])
        }
        binding.tvDetailInfo.text = description
        binding.tvDetailInfo.visibility = View.GONE
        binding.btnDetails.setOnClickListener {
            if (binding.tvDetailInfo.visibility == View.VISIBLE) {
                val anim = ObjectAnimator.ofFloat(
                    it,
                    "rotation", 180f, 0f
                )
                anim.duration = 200
                anim.start()
                binding.tvDetailInfo.visibility = View.GONE
                previousExpandItem = -1
            } else {
                closePreviousItem()
                val anim = ObjectAnimator.ofFloat(
                    it,
                    "rotation", 0f, 180f
                )
                anim.duration = 200
                anim.start()
                binding.tvDetailInfo.visibility = View.VISIBLE
                previousExpandItem = position
            }
        }
    }

    private fun setTrainingActiveItem(
        binding: TrainingItemActiveBinding,
        item: TrainingDayEntity.TrainingDay,
        position: Int
    ) {
        binding.tvDay.text = String.format(
            context.getString(R.string.day_title),
            item.day
        )
        var description = ""
        val pattern = context.getString(R.string.training_description)
        for (i in 0 until item.countOfExercise.size) {
            description += String.format(pattern, i + 1, item.countOfExercise[i])
        }
        binding.tvDetailInfo.text = description
        binding.tvDetailInfo.visibility = View.GONE
        binding.btnDetails.setOnClickListener {
            if (binding.tvDetailInfo.visibility == View.VISIBLE) {
                val anim = ObjectAnimator.ofFloat(
                    it,
                    "rotation", 180f, 0f
                )
                anim.duration = 200
                anim.start()
                binding.tvDetailInfo.visibility = View.GONE
                previousExpandItem = -1
            } else {
                closePreviousItem()
                val anim = ObjectAnimator.ofFloat(
                    it,
                    "rotation", 0f, 180f
                )
                anim.duration = 200
                anim.start()
                binding.tvDetailInfo.visibility = View.VISIBLE
                previousExpandItem = position
            }
        }
        binding.root.setOnClickListener {
            onTrainingClickListener?.invoke(item)
        }
    }

    private fun setRestItem(binding: RestItemBinding, item: TrainingDayEntity.RestDay) {
        binding.tvRest.text = String.format(
            context.getString(R.string.day_title),
            item.day
        )
    }

    private fun setRestActiveItem(binding: RestItemActiveBinding, item: TrainingDayEntity.RestDay) {
        binding.tvRest.text = String.format(
            context.getString(R.string.day_title),
            item.day
        )
        binding.root.setOnClickListener {
            onRestClickListener?.invoke(item)
        }
    }

    private fun setPassedItem(binding: PassedItemBinding, item: TrainingDayEntity) {
        when (item) {
            is TrainingDayEntity.TrainingDay -> {
                binding.tvRest.text = String.format(
                    context.getString(R.string.day_title),
                    item.day
                )
                binding.root.setOnClickListener {
                    onTrainingClickListener?.invoke(item)
                }
            }
            is TrainingDayEntity.RestDay -> {
                binding.tvRest.text = String.format(
                    context.getString(R.string.day_title),
                    item.day
                )
                binding.root.setOnClickListener {
                    onRestClickListener?.invoke(item)
                }
            }
            else -> {
                throw RuntimeException("Unresolved entity type: ${item::class}")
            }
        }
    }

    private fun closePreviousItem() {
        if (previousExpandItem > -1) {
            notifyItemChanged(previousExpandItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is TrainingDayEntity.Week -> {
                VIEW_TYPE_WEEK
            }
            is TrainingDayEntity.TrainingDay -> {
                if (position == activeItem) {
                    VIEW_TYPE_TRAINING_ACTIVE
                } else if (item.isPassed) {
                    VIEW_TYPE_PASSED
                } else {
                    VIEW_TYPE_TRAINING
                }
            }
            is TrainingDayEntity.RestDay -> {
                if (position == activeItem) {
                    VIEW_TYPE_REST_ACTIVE
                } else if (item.isPassed) {
                    VIEW_TYPE_PASSED
                } else {
                    VIEW_TYPE_REST
                }
            }
        }
    }

    companion object {
        const val VIEW_TYPE_WEEK = 100
        const val VIEW_TYPE_TRAINING = 101
        const val VIEW_TYPE_REST = 102
        const val VIEW_TYPE_TRAINING_ACTIVE = 103
        const val VIEW_TYPE_REST_ACTIVE = 104
        const val VIEW_TYPE_PASSED = 105

        const val MAX_POOL_SIZE_WEEK = 3
        const val MAX_POOL_SIZE_TRAINING = 10
        const val MAX_POOL_SIZE_REST = 10
        const val MAX_POOL_SIZE_TRAINING_ACTIVE = 1
        const val MAX_POOL_SIZE_REST_ACTIVE = 1
    }
}