package ru.dzyubamichael.pushupswithai.presentation.splash

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.dzyubamichael.pushupswithai.R
import ru.dzyubamichael.pushupswithai.databinding.FragmentSplashBinding
import ru.dzyubamichael.pushupswithai.presentation.viewBinding

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding by viewBinding(FragmentSplashBinding::bind)

    private val viewModel: SplashViewModel by viewModels()

    private val activityResultLauncher: ActivityResultLauncher<Array<String>>

    init {
        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            var allAreGranted = true
            for (b in result.values) {
                allAreGranted = allAreGranted && b
            }
            if (allAreGranted) {
                startScreen(R.id.action_splashFragment_to_mainFragment)
            } else {
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.grant_permission),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.launchNextScreen.observe(viewLifecycleOwner) {
            if (viewModel.isFirstStart()) {
                startScreen(R.id.action_splashFragment_to_cameraAiFragment)
            } else {
                val appPerms = arrayOf(Manifest.permission.CAMERA)
                activityResultLauncher.launch(appPerms)
            }
        }
        viewModel.loadingProgress.observe(viewLifecycleOwner) {
            binding.progressLoading.progress = it
        }

    }

    private fun startScreen(@IdRes id: Int) {
        findNavController().navigate(id)
    }
}