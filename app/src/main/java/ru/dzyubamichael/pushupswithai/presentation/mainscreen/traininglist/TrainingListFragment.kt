package ru.dzyubamichael.pushupswithai.presentation.mainscreen.traininglist

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.FragmentTrainingListBinding
import ru.dzyubamichael.pushupswithai.domain.TrainingDayEntity
import ru.dzyubamichael.pushupswithai.domain.TrainingLevel
import ru.dzyubamichael.pushupswithai.presentation.mainscreen.MainFragmentDirections
import ru.dzyubamichael.pushupswithai.presentation.mainscreen.traininglist.adapter.TrainingListAdapter
import ru.dzyubamichael.pushupswithai.presentation.viewBinding

@AndroidEntryPoint
class TrainingListFragment : Fragment(R.layout.fragment_training_list) {

    private val binding by viewBinding(FragmentTrainingListBinding::bind)

    private val viewModel: TrainingListViewModel by viewModels()

    private val listAdapter by lazy {
        TrainingListAdapter(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        val level = arguments?.getSerializable(TRAINING_LEVEL_KEY) as TrainingLevel
        viewModel.getTrainingList(level).observe(viewLifecycleOwner) {
            val activeItem = getActiveItem(it)
            if (activeItem != null) {
                listAdapter.updateList(it, activeItem)
                binding.progressBar.hide()
                binding.rvTrainingList.startNestedScroll(
                    View.SCROLL_AXIS_VERTICAL,
                    ViewCompat.TYPE_NON_TOUCH
                )
                binding.rvTrainingList.smoothScrollToPosition(activeItem + 6)
                binding.rvTrainingList.stopNestedScroll()
            } else {
                listAdapter.submitList(it)
                binding.progressBar.hide()
            }

        }
    }

    private fun setRecyclerView() {
        with(binding.rvTrainingList) {
            adapter = listAdapter
            listAdapter.onTrainingClickListener = {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToTrainingStartFragment(it)
                )
            }
            listAdapter.onRestClickListener = {
                findNavController().navigate(
                    MainFragmentDirections.actionMainFragmentToRestDayFragment(it)
                )
            }
            itemAnimator = null
            recycledViewPool.setMaxRecycledViews(
                TrainingListAdapter.VIEW_TYPE_WEEK,
                TrainingListAdapter.MAX_POOL_SIZE_WEEK

            )
            recycledViewPool.setMaxRecycledViews(
                TrainingListAdapter.VIEW_TYPE_TRAINING,
                TrainingListAdapter.MAX_POOL_SIZE_TRAINING

            )
            recycledViewPool.setMaxRecycledViews(
                TrainingListAdapter.VIEW_TYPE_TRAINING_ACTIVE,
                TrainingListAdapter.MAX_POOL_SIZE_TRAINING_ACTIVE
            )
            recycledViewPool.setMaxRecycledViews(
                TrainingListAdapter.VIEW_TYPE_REST,
                TrainingListAdapter.MAX_POOL_SIZE_REST

            )
            recycledViewPool.setMaxRecycledViews(
                TrainingListAdapter.VIEW_TYPE_REST_ACTIVE,
                TrainingListAdapter.MAX_POOL_SIZE_REST_ACTIVE

            )
        }

    }

    private fun getActiveItem(listTraining: List<TrainingDayEntity>): Int? {
        var result: Int? = null
        for (itemPos in 0 until listTraining.size) {
            val item = listTraining[itemPos]
            when (item) {
                is TrainingDayEntity.TrainingDay -> {
                    if (!item.isPassed) {
                        result = itemPos
                        break
                    }
                }
                is TrainingDayEntity.RestDay -> {
                    if (!item.isPassed) {
                        result = itemPos
                        break
                    }
                }
                is TrainingDayEntity.Week -> {

                }
            }
        }
        return result
    }

    companion object {

        fun newInstance(level: TrainingLevel) =
            TrainingListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(TRAINING_LEVEL_KEY, level)

                }
            }

        private const val TRAINING_LEVEL_KEY = "training_level"
    }
}