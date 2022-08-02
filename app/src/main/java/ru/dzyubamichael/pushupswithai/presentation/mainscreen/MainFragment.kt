package ru.dzyubamichael.pushupswithai.presentation.mainscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.FragmentMainBinding
import ru.dzyubamichael.pushupswithai.domain.TrainingLevel
import ru.dzyubamichael.pushupswithai.presentation.mainscreen.adapter.FragmentListViewPagerAdapter
import ru.dzyubamichael.pushupswithai.presentation.mainscreen.traininglist.TrainingListFragment
import ru.dzyubamichael.pushupswithai.presentation.viewBinding


class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
        setTabLayout()
    }


    private fun setViewPager(){
        val fragmentList = listOf(
            TrainingListFragment.newInstance(TrainingLevel.EASY),
            TrainingListFragment.newInstance(TrainingLevel.MEDIUM),
            TrainingListFragment.newInstance(TrainingLevel.HARD),
        )
        val adapter = FragmentListViewPagerAdapter(
            childFragmentManager, lifecycle,
            fragmentList
        )
        binding.listFragmentContainer.adapter = adapter

    }

    private fun setTabLayout() {
        TabLayoutMediator(binding.levelTabLayout, binding.listFragmentContainer) { tab, position ->

        }.attach()
    }
}