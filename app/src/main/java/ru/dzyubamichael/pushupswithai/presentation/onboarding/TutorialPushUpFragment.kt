package ru.dzyubamichael.pushupswithai.presentation.onboarding

import androidx.fragment.app.Fragment
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.FragmentTutorialPushUpBinding
import ru.dzyubamichael.pushupswithai.presentation.viewBinding

class TutorialPushUpFragment : Fragment(R.layout.fragment_tutorial_push_up) {

    private val binding by viewBinding(FragmentTutorialPushUpBinding::bind)

    companion object{
        fun newInstance() = TutorialPushUpFragment()
    }
}