package com.aio.calculator.core.common

/** A small typed result used by calculator/domain code. */
sealed class Result<out T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Failure(val exception: Throwable) : Result<Nothing>()

    fun isSuccess(): Boolean = this is Success<*>
    fun isFailure(): Boolean = this is Failure
    fun getOrNull(): T? = (this as? Success<T>)?.value
    fun getOrThrow(): T = when (this) {
        is Success -> value
        is Failure -> throw exception
    }

    fun getOrElse(default: () -> @UnsafeVariance T): T = getOrNull() ?: default()
    fun onSuccess(action: (T) -> Unit): Result<T> = apply { (this as? Success<T>)?.let { action(it.value) } }
    fun onFailure(action: (Throwable) -> Unit): Result<T> = apply { (this as? Failure)?.let { action(it.exception) } }

    fun <R> map(transform: (T) -> R): Result<R> = when (this) {
        is Success -> Success(transform(value))
        is Failure -> Failure(exception)
    }

    fun <R> flatMap(transform: (@UnsafeVariance T) -> Result<R>): Result<R> = when (this) {
        is Success -> transform(value)
        is Failure -> Failure(exception)
    }

    companion object {
        fun <T> success(value: T): Result<T> = Success(value)
        fun <T> failure(exception: Throwable): Result<T> = Failure(exception)
    }
}

fun <T> kotlin.Result<T>.toAioResult(): Result<T> =
    fold({ Result.success(it) }, { Result.failure(it) })

open class CalculationError(message: String, cause: Throwable? = null) : Exception(message, cause)
class InvalidInputError(message: String, cause: Throwable? = null) : CalculationError(message, cause)
class DivisionByZeroError(message: String = "Cannot divide by zero") : CalculationError(message)
class OverflowError(message: String = "Number too large") : CalculationError(message)
class DataUnavailableError(message: String, cause: Throwable? = null) : Exception(message, cause)
