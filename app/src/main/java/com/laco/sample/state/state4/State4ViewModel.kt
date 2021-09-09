package com.laco.sample.state.state4

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
class State4ViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    data class State(
        val name: String = "",
        val age: String = ""
    ) {
        val isEmpty: Boolean = name.isEmpty() && age.isEmpty()
    }

    private val _state: MutableStateFlow<State> = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error.asStateFlow()

    init {
        viewModelScope.launch {
            _loading.value = true
            val result = userRepository.getUser()

            result
                .onSuccess { user ->
                    _loading.value = false
                    _state.update { state ->
                        state.copy(
                            name = user.name,
                            age = user.age.toString()
                        )
                    }
                }
                .onFailure {
                    _loading.value = false
                    _error.value = true
                }
        }
    }
}
