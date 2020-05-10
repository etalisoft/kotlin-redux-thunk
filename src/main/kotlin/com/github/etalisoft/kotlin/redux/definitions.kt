package com.github.etalisoft.kotlin.redux

typealias Thunk<T> = suspend (dispatch: Dispatch, getState: GetState<T>) -> Any
typealias ThunkExtra<T> = suspend (dispatch: Dispatch, getState: GetState<T>, extraArgument: Any?) -> Any
