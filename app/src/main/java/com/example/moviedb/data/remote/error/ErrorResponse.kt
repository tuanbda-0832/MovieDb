package com.example.moviedb.data.remote.error

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ErrorResponse {

    @Expose
    @SerializedName("error")
    private val mError: Error? = null

    val error: Error
        get() = mError ?: Error()

    inner class Error {
        @Expose
        @SerializedName("code")
        val code: Int = 0
        @Expose
        @SerializedName("description")
        private val messages: List<String>? = null
        val message: String? = null
    }
}
