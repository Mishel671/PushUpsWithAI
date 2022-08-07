package ru.dzyubamichael.pushupswithai.presentation.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.dzyubamichael.pushupswithai.databinding.FragmentPreparationTrainingBinding
import ru.dzyubamichael.pushupswithai.domain.TrainingDayEntity


class PreparationTrainingFragment : Fragment() {

    private var _binding: FragmentPreparationTrainingBinding? = null
    private val binding: FragmentPreparationTrainingBinding
        get() = _binding ?: throw RuntimeException("")

    private val args by navArgs<PreparationTrainingFragmentArgs>()

    private var trainingItem: TrainingDayEntity.TrainingDay? = null
    private var exerciseId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPreparationTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseArgs()
        setProgressBar()
        binding.tvCount.text = trainingItem!!.countOfExercise[exerciseId].toString()
        binding.btnStart.setOnClickListener {
            findNavController().navigate(
                PreparationTrainingFragmentDirections
                    .actionPreparationTrainingFragmentToTrainingCameraFragment(
                        args.trainingDay,
                        exerciseId
                    )
            )
        }
    }

    private fun parseArgs() {
        trainingItem = args.trainingDay
        exerciseId = args.exerciseId
    }

    private fun setProgressBar() {
        binding.trainingProgress.max = trainingItem!!.countOfExercise.size
        val exercise = exerciseId + 1
        binding.trainingProgress.progress = exercise
        binding.tvExercise.text = exercise.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}