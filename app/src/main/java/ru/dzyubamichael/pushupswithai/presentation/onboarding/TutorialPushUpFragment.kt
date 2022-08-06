package ru.dzyubamichael.pushupswithai.presentation.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.FragmentTutorialPushUpBinding
import ru.dzyubamichael.pushupswithai.presentation.viewBinding

@AndroidEntryPoint
class TutorialPushUpFragment : Fragment(R.layout.fragment_tutorial_push_up) {

    private val binding by viewBinding(FragmentTutorialPushUpBinding::bind)

    private val viewModel by viewModels<TutorialPushUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.saveFirstStart()
        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_tutorialPushUpFragment_to_mainFragment)
        }
    }

}