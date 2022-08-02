package ru.dzyubamichael.pushupswithai.presentation.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.FragmentOnBoardingBinding
import ru.dzyubamichael.pushupswithai.presentation.viewBinding

@AndroidEntryPoint
class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {

    private val binding by viewBinding(FragmentOnBoardingBinding::bind)

    private val viewModel: OnBoardingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.saveFirstStart()
        setViewPager()
        setTabLayout()
    }

    private fun setViewPager(){
        val fragmentList = listOf(
            TutorialPushUpFragment.newInstance(),
            CameraAiFragment.newInstance()
        )
        val adapter = OnBoardingViewPagerAdapter(
            childFragmentManager, lifecycle,
            fragmentList
        )
        binding.vpContainer.adapter = adapter

    }

    private fun setTabLayout() {
        TabLayoutMediator(binding.tabIndicator, binding.vpContainer) { tab, position ->
        }.attach()
    }

}