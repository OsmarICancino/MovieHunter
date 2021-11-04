package com.dalvikmx.moviehunter.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(

    @PrimaryKey
    val id: Long,

    //ratings
    val voteAverage: Double,
    val popularity: Double,

    //images // https://image.tmdb.org/t/p/w185/lvjscO8wmpEbIfOEZi92Je8Ktlg.jpg
    val backdropPath: String,
    val posterPath: String,

    //title
    val originalTitle: String,

    //date
    val releaseDate: String,

    //Synopsis
    val overview: String

)