# Kotlin-Redux-Thunk

> A [Redux Thunk](https://github.com/reduxjs/redux-thunk) implementation for Kotlin

The thunk middleware empowers your Redux store with the ability to process actions asyncronously.

## `createThunkMiddleware`  
```kotlin
val store = createStore(
    ::reducer, 
    MyAppState(), 
    applyMiddleware(createThunkMiddleware())
)
```

## `thunk`
The `thunk` helper function creates a `Thunk<T>` or `ThunkExtra<T>` for you and allows you to
dispatch actions asynchronously. 
```kotlin
fun changeNameAsync() = thunk<MyAppState> { dispatch, getState /*, extraArg */ ->
    dispatch(ChangeName(first = "Bob"))
    delay(100)
    dispatch(ChangeName(last = "Smith"))
}

store.dispatch(ChangeName("John", "Doe"))
store.dispatch(changeNameAsync())
```
