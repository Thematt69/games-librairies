package com.md.games

class GamesModel(
    val id: String,
    val description: String = "Aucune description disponible",
    val developmentTeam: String = "Inconnu",
    val editorialTeam: String = "Inconnu",
    val headerImageUrl: String = "https://cdn.pixabay.com/photo/2015/09/05/22/49/video-games-925929_960_720.jpg",
    val price: Number = 0.0,
    val releaseDate: String = "Date inconnu",
    val status: Boolean = false,
    val steamUrl: String = "https://store.steampowered.com/",
    val title: String = "Titre inconnu",
)
