package com.raminabbasiiii.movies.data.mapper

import com.example.khabeer_task.domain.entities.User_Entities
import com.raminabbasiiii.movies.data.responeses.MovieDto
import com.raminabbasiiii.movies.data.responeses.User_Response
import com.raminabbasiiii.movies.domain.entities.Movie
import com.raminabbasiiii.movies.domain.entities.MovieDetails

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        poster = poster,
        year = year,
        country = country,
        rating = rating,
        genres = genres,
        images = images
    )
}

fun MovieDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = id,
        title = title,
        poster = poster,
        year = year,
        country = country,
        rating = rating,
        rated = rated,
        released = released,
        runtime = runtime,
        director = director,
        writer = writer,
        actors = actors,
        plot = plot,
        awards = awards,
        votes = votes,
        genres = genres,
        images = images
    )
}


fun User_Response.toUser(): User_Entities {
    return User_Entities(
        UserType = UserType,
        Activation = Activation,
        Token = Token,
        UserRole = UserRole,
        AccountRole = AccountRole,
        AccountId = AccountId,
        Success = Success,
        Code = Code,
        EnglishMessage = EnglishMessage,
        ArabicMessage = ArabicMessage,
        CurrentPage = CurrentPage,
        IsArabic = IsArabic,
        PageCount = PageCount,
        VisitStatus = VisitStatus,

    )
}




