package ru.dzyubamichael.pushupswithai.presentation.training.end

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.FragmentTrainingEndBinding
import ru.dzyubamichael.pushupswithai.presentation.viewBinding


@AndroidEntryPoint
class TrainingEndFragment : Fragment(R.layout.fragment_training_end) {

    private val binding by viewBinding(FragmentTrainingEndBinding::bind)

    private val args by navArgs<TrainingEndFragmentArgs>()

    private val viewModel: TrainingEndViewModel by viewModels()

    private val trainingItem by lazy {
        args.trainingItem
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle()
        viewModel.saveTraining(trainingItem.id)
        binding.btnStart.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setTitle() {
        var totalCount = 0
        for (countExercise in trainingItem.countOfExercise) {
            totalCount += countExercise
        }
        binding.tvResult.text = String.format(getString(R.string.end_description), totalCount)
    }

}