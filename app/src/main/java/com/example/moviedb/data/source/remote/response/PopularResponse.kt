package com.example.moviedb.data.source.local.remote.response

import com.example.moviedb.data.model.Movie
import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @SerializedName("page") val page: Int? = null,
    @SerializedName("total_results") val total_results: Int? = null,
    @SerializedName("total_pages") val total_pages: Int? = null,
    @SerializedName("results") val movies: List<Movie>? = null
)