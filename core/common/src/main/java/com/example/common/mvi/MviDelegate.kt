package com.example.common.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MviDelegate<S : UiState, E : UiEffect>(
    initialState: S,
    private val scope: CoroutineScope
) {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<E>()
    val effect: SharedFlow<E> = _effect

    fun currentState(): S = _state.value

    fun setState(reducer: S.() -> S) {
        _state.value = _state.value.reducer()
    }

    fun sendEffect(effect: E) {
        scope.launch { _effect.emit(effect) }
    }

    fun launch(block: suspend CoroutineScope.() -> Unit) {
        scope.launch(block = block)
    }
}