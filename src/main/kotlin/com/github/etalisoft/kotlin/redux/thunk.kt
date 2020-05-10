package com.github.etalisoft.kotlin.redux

fun <State> thunk(block: Thunk<State>) = block

fun <State> thunk(block: ThunkExtra<State>) = block
