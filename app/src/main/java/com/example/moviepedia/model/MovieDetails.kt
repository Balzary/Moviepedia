package com.example.moviepedia.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetails(

    @SerializedName("title")
    var nome: String,

    @SerializedName("poster_path")
    var poster: String,

    @SerializedName("genres")
    var genero: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("release_date")
    var launchDate: String,

    @SerializedName("original_language")
    var language: String

)  : Parcelable {
    constructor() : this("", "", "", "","", "")
}
