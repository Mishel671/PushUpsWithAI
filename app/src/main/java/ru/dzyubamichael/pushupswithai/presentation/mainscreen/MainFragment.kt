package ru.dzyubamichael.pushupswithai.presentation.mainscreen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
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

    private fun setViewPager() {
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
        val tabTitles = listOf(
            getString(R.string.tab_easy_level),
            getString(R.string.tab_medium_level),
            getString(R.string.tab_hard_level),
        )
        TabLayoutMediator(binding.levelTabLayout, binding.listFragmentContainer) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    companion object {
        const val RESULT_FROM_FRAGMENT_B = "RESULT_FROM_FRAGMENT_B"
    }
}