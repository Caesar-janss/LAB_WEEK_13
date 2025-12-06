package com.example.lab_week_13

import com.example.lab_week_13.api.MovieService
import com.example.lab_week_13.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(private val movieService: MovieService) {

    private val apiKey = "36471aa7bbb67281a3ea85535adfc74c"

    fun fetchMovies(): Flow<List<Movie>> = flow {
        val response = movieService.getPopularMovies(apiKey)
        emit(response.results)
    }
}
