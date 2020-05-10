package com.github.etalisoft.kotlin.redux

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlinx.coroutines.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CreateStoreTests {
    private data class State(val x: Int = 0, val y: Int = 0)
    private data class Increment(val x: Int? = null, val y: Int? = null)
    private fun incrementByHalf() = thunk<State> { dispatch, getState ->
        dispatch(Increment(x = getState().x / 2))
        delay(1)
        dispatch(Increment(y = getState().y / 2))
    }
    private fun reducer(state: State, action: Any) = when(action) {
        is Increment -> State(state.x + (action.x ?: 0), state.y + (action.y ?: 0))
        else -> state
    }

    private lateinit var store: Store<State>

    @BeforeEach
    fun beforeEach() {
        store = createStore(::reducer, State(), applyMiddleware(createThunkMiddleware()))
    }

    @Test
    fun `should dispatch a suspend thunk`() = runBlocking {
        store.dispatch(Increment(2, 10))
        assertThat(store.state).isEqualTo(State(2, 10))
        store.dispatch(incrementByHalf())
        assertThat(store.state).isEqualTo(State(3, 15))
    }
}
