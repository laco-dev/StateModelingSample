package com.laco.sample.state.state1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laco.sample.state.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class State1ViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error.asStateFlow()

    private val _name: MutableStateFlow<String> = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _age: MutableStateFlow<String> = MutableStateFlow("")
    val age: StateFlow<String> = _age.asStateFlow()

    init {
        viewModelScope.launch {
            _loading.value = true
            val result = userRepository.getUser()
            result
                .onSuccess { user ->
                    _loading.value = false
                    _error.value = false
                    _name.value = user.name
                    _age.value = user.age.toString()
                }
                .onFailure {
                    _loading.value = false
                    _error.value = true
                }
        }
    }
}
