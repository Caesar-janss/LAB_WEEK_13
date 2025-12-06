package com.example.lab_week_13.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "movies")
data class Movie(

    @PrimaryKey
    val id: Int,

    val title: String,

    val overview: String?,

    @Json(name = "release_date")
    val releaseDate: String?,

    val popularity: Double,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "backdrop_path")
    val backdropPath: String?
)
