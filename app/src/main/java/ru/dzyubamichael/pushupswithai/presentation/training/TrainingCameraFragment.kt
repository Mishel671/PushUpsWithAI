package ru.dzyubamichael.pushupswithai.presentation.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.FragmentTrainingCameraBinding
import ru.dzyubamichael.pushupswithai.presentation.viewBinding


class TrainingCameraFragment : Fragment(R.layout.fragment_training_camera) {

    private val binding by viewBinding(FragmentTrainingCameraBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training_camera, container, false)
    }

}