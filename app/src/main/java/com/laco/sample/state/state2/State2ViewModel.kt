package com.laco.sample.state.state2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laco.sample.state.data.User
import com.laco.sample.state.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class State2ViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    sealed class State {

        data class Success(
            val name: String,
            val age: String
        ) : State()

        object Failure : State()

        object Loading : State()
    }

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.value = State.Loading
            val result = userRepository.getUser()
            result
                .onSuccess { user ->
                    _state.value = State.Success(user.name, user.age.toString())
                }
                .onFailure {
                    _state.value = State.Failure
                }
        }
    }
}
