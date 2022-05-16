package com.example.moviepedia.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(

    @SerializedName("id")
    val id : Int?,

    @SerializedName("title")
    val title : String,

    @SerializedName("poster_path")
    val poster : String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("genre_ids")
    val genero : List<Int>,

    @SerializedName("release_date")
    val releaseDate : String,

    @SerializedName("original_language")
    var language: String

) : Parcelable{
    constructor() : this(0, "", "", "", mutableListOf(), "", "")
}