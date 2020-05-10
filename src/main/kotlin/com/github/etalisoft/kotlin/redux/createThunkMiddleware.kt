package com.github.etalisoft.kotlin.redux

import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
fun <T> createThunkMiddleware(extraArgument: Any? = null): Middleware<T> = { store ->
    { next ->
        { action ->
            when {
                action !is Function<*> -> next(action)
                action as? Thunk<*> != null -> action(store.dispatch, store.getState)
                action as? ThunkExtra<*> != null -> action(store.dispatch, store.getState, extraArgument)
                else -> throw IllegalArgumentException("Dispatching functions must use type Thunk or ThunkExtra")
            }
        }
    }
}
