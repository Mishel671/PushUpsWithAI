package ru.dzyubamichael.pushupswithai.presentation.onboarding

import androidx.fragment.app.Fragment
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.FragmentCameraAiBinding
import ru.dzyubamichael.pushupswithai.presentation.viewBinding

class CameraAiFragment : Fragment(R.layout.fragment_camera_ai) {

    private val binding by viewBinding(FragmentCameraAiBinding::bind)

    companion object {
        fun newInstance() = CameraAiFragment()
    }
}