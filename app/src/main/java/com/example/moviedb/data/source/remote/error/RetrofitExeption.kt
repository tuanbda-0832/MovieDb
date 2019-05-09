package com.example.moviedb.data.source.local.remote.error

import retrofit2.Response

class RetrofitException : RuntimeException {

    companion object {

        fun toNetworkError(cause: Throwable): RetrofitException {
            return RetrofitException(Type.NETWORK, cause)
        }

        fun toHttpError(response: Response<*>): RetrofitException {
            return RetrofitException(Type.HTTP, response)
        }

        fun toUnexpectedError(cause: Throwable): RetrofitException {
            return RetrofitException(Type.UNEXPECTED, cause)
        }

        fun toServerError(response: ErrorResponse): RetrofitException {
            return RetrofitException(Type.SERVER, response)
        }
    }

    private val _errorType: String
    private lateinit var _response: Response<*>
    private var _errorResponse: ErrorResponse? = null

    private constructor(type: String, cause: Throwable) : super(cause.message, cause) {
        _errorType = type
    }

    private constructor(type: String, response: Response<*>) {
        _errorType = type
        _response = response
    }

    constructor(type: String, response: ErrorResponse?) {
        _errorType = type
        _errorResponse = response
    }

    fun getMessageError(): String? =
        when (_errorType) {
            Type.SERVER -> _errorResponse?.error?.message
            Type.NETWORK -> getNetworkErrorMessage(cause)
            Type.HTTP -> _response.code().getHttpErrorMessage()
            else -> "Error"
        }

    private fun getNetworkErrorMessage(throwable: Throwable?): String = throwable?.message.toString()

    private fun Int.getHttpErrorMessage(): String = when (this) {
        in 300..308 -> "It was transferred to a different URL. I'm sorry for causing you trouble"
        in 400..451 -> "An error occurred on the application side. Please try again later!"
        in 500..511 -> "A server error occurred. Please try again later!"
        else -> "An error occurred. Please try again later!"
    }
}
