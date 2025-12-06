package com.example.lab_week_13

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_week_13.model.Movie
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import java.util.Calendar

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>> get() = _popularMovies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.fetchMovies()
                .catch { e ->
                    _error.postValue("Error: ${e.message}")
                }
                .collect { movies ->

                    val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()

                    val filtered = movies.filter { movie ->
                        movie.releaseDate?.startsWith(currentYear) == true
                    }

                    _popularMovies.postValue(filtered)
                }
        }
    }
}
