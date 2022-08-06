package ru.dzyubamichael.pushupswithai.presentation.training

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.FragmentPreparationTrainingBinding
import ru.dzyubamichael.pushupswithai.presentation.viewBinding


class PreparationTrainingFragment : Fragment(R.layout.fragment_preparation_training) {

    private val binding by viewBinding(FragmentPreparationTrainingBinding::bind)

    private val args by navArgs<PreparationTrainingFragmentArgs>()


}