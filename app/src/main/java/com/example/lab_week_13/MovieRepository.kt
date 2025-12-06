package com.example.lab_week_13

import com.example.lab_week_13.api.MovieService
import com.example.lab_week_13.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(private val movieService: MovieService) {
    private val apiKey = "36471aa7bbb67281a3ea85535adfc74c"

    // Fungsi fetchMovies sekarang mengembalikan Flow
    // Panggilan jaringan terjadi saat flow ini di-collect
    fun fetchMovies(): Flow<List<Movie>> = flow {
        val popularMovies = movieService.getPopularMovies(apiKey)
        emit(popularMovies.results)
    }
}