package com.example.sandbox_compose.domain.model

data class GameUIModel(
       val id: Int,
       val title: String,
       val thumbnail: String,
       val shortDescription: String,
       val gameURL: String,
       val genre: String,
       val platform: String,
       val publisher: String,
       val developer: String,
       val releaseDate: String,
       val freetogameProfileURL: String
)

val demoGames = (1..10).map {
    GameUIModel(
           id = 347,
           title = "Elvenar",
           thumbnail = "https://www.freetogame.com/g/347/thumbnail.jpg",
           shortDescription = "A browser based city-building strategy MMO set in the fantasy world of Elvenar.",
           gameURL = "https://www.freetogame.com/open/elvenar",
           genre = "Strategy",
           platform = "Web Browser",
           publisher = "InnoGames",
           developer = "InnoGames",
           releaseDate = "2015-04-08",
           freetogameProfileURL = "https://www.freetogame.com/elvenar",
    )
}
