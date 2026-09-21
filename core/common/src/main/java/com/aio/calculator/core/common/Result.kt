package com.aio.calculator.core.common

import kotlin.Result

/**
 * A sealed class representing the result of an operation that can succeed or fail.
 * This is a more expressive alternative to exceptions for expected failure cases.
 */
sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Failure(val exception: Throwable) : Result<Nothing>()

    companion object {
        fun <T> success(value: T): Result<T> = Success(value)
        fun <T> failure(exception: Throwable): Result<T> = Failure(exception)
    }

    fun isSuccess(): Boolean = this is Success
    fun isFailure(): Boolean = this is Failure

    fun getOrNull(): T? = when (this) {
        is Success -> value
        is Failure -> null
    }

    fun getOrElse(default: () -> T): T = when (this) {
        is Success -> value
        is Failure -> default()
    }

    fun getOrThrow(): T = when (this) {
        is Success -> value
        is Failure -> throw exception
    }

    fun <R> map(transform: (T) -> R): Result<R> = when (this) {
        is Success -> Success(transform(value))
        is Failure -> Failure(exception)
    }

    fun <R> flatMap(transform: (T) -> Result<R>): Result<R> = when (this) {
        is Success -> transform(value)
        is Failure -> Failure(exception)
    }

    fun onSuccess(action: (T) -> Unit): Result<T> = apply { if (isSuccess()) action(value) }
    fun onFailure(action: (Throwable) -> Unit): Result<T> = apply { if (isFailure()) action(exception) }
}

/**
 * Extension functions for standard Kotlin Result
 */
fun <T> Result<T>.toAioResult(): Result<T> = when (this) {
    is Result.Success -> Result.success(value)
    is Result.Failure -> Result.failure(exception)
}

/**
 * A generic exception for calculation errors
 */
class CalculationError(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)

/**
 * Exception for invalid input
 */
class InvalidInputError(
    message: String,
    cause: Throwable? = null
) : CalculationError(message, cause)

/**
 * Exception for division by zero
 */
class DivisionByZeroError(message: String = "Cannot divide by zero") : CalculationError(message)

/**
 * Exception for overflow
 */
class OverflowError(message: String = "Number too large") : CalculationError(message)

/**
 * Exception for unavailable data
 */
class DataUnavailableError(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)