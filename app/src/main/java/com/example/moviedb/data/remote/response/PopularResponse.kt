package com.example.moviedb.data.remote.response

import com.example.moviedb.data.model.Movie
import com.google.gson.annotations.SerializedName

data class PopularResponse (
    @SerializedName("page") val page : Int,
    @SerializedName("total_results") val total_results : Int,
    @SerializedName("total_pages") val total_pages : Int,
    @SerializedName("results") val movies : List<Movie>
)
