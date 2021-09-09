package com.laco.sample.state.state4

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.laco.sample.state.StateActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class State4Activity : StateActivity() {

    private val viewModel: State4ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                binding.card.isVisible = !state.isEmpty
                binding.name.text = state.name
                binding.age.text = state.age
            }
        }

        lifecycleScope.launch {
            viewModel.error.collect { error ->
                binding.error.isVisible = error
            }
        }

        lifecycleScope.launch {
            viewModel.loading.collect { loading ->
                binding.loading.isVisible = loading
            }
        }
    }
}
