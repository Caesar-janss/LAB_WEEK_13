package com.example.lab_week_13

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lab_week_13.databinding.ActivityMainBinding
import com.example.lab_week_13.model.Movie

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val movieAdapter by lazy {
        MovieAdapter(object : MovieAdapter.MovieClickListener {
            override fun onMovieClick(movie: Movie) {
                openMovieDetails(movie)
            }
        })
    }

    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory((application as MovieApplication).movieRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.movieList.layoutManager = GridLayoutManager(this, 2)
        binding.movieList.adapter = movieAdapter

        movieViewModel.popularMovies.observe(this) { movies ->
            movieAdapter.submitList(movies)
        }
    }

    private fun openMovieDetails(movie: Movie) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra(DetailsActivity.EXTRA_TITLE, movie.title)
            putExtra(DetailsActivity.EXTRA_RELEASE, movie.releaseDate)
            putExtra(DetailsActivity.EXTRA_OVERVIEW, movie.overview)
            putExtra(DetailsActivity.EXTRA_POSTER, movie.posterPath)
        }
        startActivity(intent)
    }
}
