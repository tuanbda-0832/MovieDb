package com.example.moviedb.data.source.remote.response

import com.example.moviedb.data.model.Genre
import com.google.gson.annotations.SerializedName

data class GenresReponse(

    @SerializedName("genres") val genres: List<Genre>
)