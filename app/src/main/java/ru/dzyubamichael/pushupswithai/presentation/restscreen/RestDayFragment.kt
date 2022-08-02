package ru.dzyubamichael.pushupswithai.presentation.restscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.FragmentRestDayBinding
import ru.dzyubamichael.pushupswithai.presentation.viewBinding

@AndroidEntryPoint
class RestDayFragment : Fragment(R.layout.fragment_rest_day) {

    private val binding by viewBinding(FragmentRestDayBinding::bind)

    private val args by navArgs<RestDayFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = binding.tvTitle.text.toString() + args.restEntity.id.toString()
    }
}