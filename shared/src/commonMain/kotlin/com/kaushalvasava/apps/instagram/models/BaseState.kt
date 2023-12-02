package com.kaushalvasava.apps.instagram.models

import kotlinx.serialization.Serializable

/**
 * [BaseState] class can be used for basic state representation of async work
 * In case success represents multiple path that generics parameter [DATA] can also be a sealed class
 */
@Serializable
sealed class BaseState<out DATA : Any, out ERROR : Any> {
    /**
     * [Loading] state represents the initial state before success or failure of the async work
     */
    @Serializable
    object Loading : BaseState<Nothing, Nothing>()

    /**
     * [Success] Represents the success state
     * @param data represents success of the async work. It can be Unit as well
     */
    @Serializable

    data class Success<out DATA : Any>(val data: DATA) : BaseState<DATA, Nothing>()

    /**
     * [Failed] state represents the failure of the async work. Use any error model to represent the error
     */
    @Serializable
    data class Failed<out ERROR : Any>(val error: ERROR) : BaseState<Nothing, ERROR>()
}