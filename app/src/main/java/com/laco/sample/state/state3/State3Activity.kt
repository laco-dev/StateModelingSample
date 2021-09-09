package com.laco.sample.state.state3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.laco.sample.state.StateActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class State3Activity : StateActivity() {

    private val viewModel: State3ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                binding.loading.isVisible = state.loading
                binding.error.isVisible = state.error
                binding.card.isVisible = !state.loading
                binding.name.text = state.name
                binding.age.text = state.age
            }
        }
    }
}
