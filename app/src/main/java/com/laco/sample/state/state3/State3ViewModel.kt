package com.laco.sample.state.state3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laco.sample.state.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class State3ViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    data class State(
        val loading: Boolean = true,
        val error: Boolean = false,
        val name: String = "",
        val age: String = ""
    )

    private val _state: MutableStateFlow<State> = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val result = userRepository.getUser()
            _state.update { state -> state.copy(loading = true) }

            result
                .onSuccess { user ->
                    _state.update { state ->
                        state.copy(
                            loading = false,
                            name = user.name,
                            age = user.age.toString()
                        )
                    }
                }
                .onFailure {
                    _state.update { state ->
                        state.copy(
                            loading = false,
                            error = true
                        )
                    }
                }
        }
    }
}
