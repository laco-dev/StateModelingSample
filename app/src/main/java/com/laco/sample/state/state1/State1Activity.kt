package com.laco.sample.state.state1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.laco.sample.state.StateActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@AndroidEntryPoint
class State1Activity : StateActivity() {

    private val viewModel: State1ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewModel.name.collect { name ->
                binding.name.text = name
            }
        }

        lifecycleScope.launch {
            viewModel.age.collect { age ->
                binding.age.text = age
            }
        }

        lifecycleScope.launch {
            viewModel.loading.collect { loading ->
                binding.loading.isVisible = loading
            }
        }

        lifecycleScope.launch {
            viewModel.error.collect { error ->
                binding.error.isVisible = error
            }
        }

        lifecycleScope.launch {
            combine(viewModel.loading, viewModel.error) { loading, error -> !loading && !error }
                .collect { isVisible ->
                    binding.card.isVisible = isVisible
                }
        }
    }
}
