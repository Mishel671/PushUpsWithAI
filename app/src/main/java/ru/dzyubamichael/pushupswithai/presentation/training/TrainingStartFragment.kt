package ru.dzyubamichael.pushupswithai.presentation.training

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.FragmentTrainingStartBinding
import ru.dzyubamichael.pushupswithai.presentation.viewBinding


class TrainingStartFragment : Fragment(R.layout.fragment_training_start) {

    private val binding by viewBinding(FragmentTrainingStartBinding::bind)

    private val args by navArgs<TrainingStartFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonContinue.setOnClickListener {
            findNavController().navigate(
                TrainingStartFragmentDirections
                    .actionTrainingStartFragmentToPreparationTrainingFragment(args.trainingDay))
        }
    }

}