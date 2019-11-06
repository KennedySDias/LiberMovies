package com.kennedydias.domain.mapper

import com.kennedydias.data.model.MovieFullModel
import com.kennedydias.data.model.MovieRatingModel
import com.kennedydias.data.model.MovieShortModel
import com.kennedydias.domain.model.MovieFullData
import com.kennedydias.domain.model.MovieRatingData
import com.kennedydias.domain.model.MovieShortData
import org.koin.core.KoinComponent

class MovieMapper : KoinComponent {

    fun mapShort(response: MovieShortModel): MovieShortData {
        response.let {
            return MovieShortData(
                imdbID = it.imdbID,
                title = it.title ?: "",
                year = it.year ?: "",
                type = it.type ?: "",
                poster = it.poster ?: ""
            )
        }
    }

    fun mapFull(response: MovieFullModel): MovieFullData {
        response.let {
            return MovieFullData(
                title = it.title,
                year = it.year,
                rated = it.rated,
                poster = it.poster,
                released = it.released,
                runtime = it.runtime,
                genre = it.genre,
                director = it.director,
                writer = it.writer,
                actors = it.actors,
                plot = it.plot,
                language = it.language,
                country = it.country,
                awards = it.awards,
                metascore = it.metascore,
                imdbRating = it.imdbRating,
                imdbVotes = it.imdbVotes,
                imdbID = it.imdbID,
                type = it.type,
                dvd = it.dvd,
                boxOffice = it.boxOffice,
                production = it.production,
                website = it.website,
                response = it.response,
                ratings = mapRatings(it.ratings ?: emptyList())
            )
        }
    }

    fun mapRatings(response: List<MovieRatingModel>): List<MovieRatingData> {
        response.let {
            return it.map { model ->
                MovieRatingData(
                    source = model.source,
                    value = model.value
                )
            }
        }
    }


}