package br.com.movieapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(false)
    val movieId: Int,
    val title: String,
    val imgUrl: String
)
