package com.kennedydias.libermovies.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesViewModel : ViewModel() {

    val movies: MutableLiveData<List<MovieData>> = MutableLiveData()
    val series: MutableLiveData<List<MovieData>> = MutableLiveData()

    fun getMovies() {
        movies.value = listOf(
            MovieData(
                title = "Captain Marvel",
                poster =
                "https://m.media-amazon.com/images/M/MV5BMTE0YWFmOTMtYTU2ZS00ZTIxLWE3OTEtYTNiYzBkZjViZThiXkEyXkFqcGdeQXVyODMzMzQ4OTI@._V1_SX300.jpg"
            ),
            MovieData(
                title = "Marvel One-Shot: Item 47",
                poster =
                "https://m.media-amazon.com/images/M/MV5BMTM2MzY1ODcyN15BMl5BanBnXkFtZTcwNTE3OTIxOA@@._V1_SX300.jpg"
            ),
            MovieData(
                title = "Marvel One-Shot: All Hail the King",
                poster =
                "https://m.media-amazon.com/images/M/MV5BYTQzNzZiOWItOTNlMC00MjA4LWI5ZTAtODk3MmQ2MGJiYTdmXkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
            ),
            MovieData(
                title = "Lego Marvel Super Heroes",
                poster =
                "https://ia.media-imdb.com/images/M/MV5BOTA5ODA2NTI2M15BMl5BanBnXkFtZTgwNTcxMzU1MDE@._V1_SX300.jpg"
            ),
            MovieData(
                title = "Adventures of Captain Marvel",
                poster =
                "https://m.media-amazon.com/images/M/MV5BNjg0NTk3NjUyNF5BMl5BanBnXkFtZTgwNDQ5MjM1MjE@._V1_SX300.jpg"
            )
        )

        series.value = listOf(
            MovieData(
                title = "Captain Marvel",
                poster =
                "https://m.media-amazon.com/images/M/MV5BMTE0YWFmOTMtYTU2ZS00ZTIxLWE3OTEtYTNiYzBkZjViZThiXkEyXkFqcGdeQXVyODMzMzQ4OTI@._V1_SX300.jpg"
            ),
            MovieData(
                title = "Marvel One-Shot: Item 47",
                poster =
                "https://m.media-amazon.com/images/M/MV5BMTM2MzY1ODcyN15BMl5BanBnXkFtZTcwNTE3OTIxOA@@._V1_SX300.jpg"
            ),
            MovieData(
                title = "Marvel One-Shot: All Hail the King",
                poster =
                "https://m.media-amazon.com/images/M/MV5BYTQzNzZiOWItOTNlMC00MjA4LWI5ZTAtODk3MmQ2MGJiYTdmXkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg"
            ),
            MovieData(
                title = "Lego Marvel Super Heroes",
                poster =
                "https://ia.media-imdb.com/images/M/MV5BOTA5ODA2NTI2M15BMl5BanBnXkFtZTgwNTcxMzU1MDE@._V1_SX300.jpg"
            ),
            MovieData(
                title = "Adventures of Captain Marvel",
                poster =
                "https://m.media-amazon.com/images/M/MV5BNjg0NTk3NjUyNF5BMl5BanBnXkFtZTgwNDQ5MjM1MjE@._V1_SX300.jpg"
            )
        )
    }

}