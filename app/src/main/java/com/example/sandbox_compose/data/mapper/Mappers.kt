package com.example.sandbox_compose.data.mapper

import com.example.sandbox_compose.data.model.RemoteGameListItem
import com.example.sandbox_compose.domain.model.GameUIModel

fun RemoteGameListItem.toUIModel(): GameUIModel {
    return GameUIModel(
           id = this.id ?: 0,
           title = this.title.orEmpty(),
           thumbnail = this.thumbnail.orEmpty(),
           shortDescription = this.shortDescription.orEmpty(),
           gameURL = this.gameUrl.orEmpty(),
           genre = this.genre.orEmpty(),
           platform = this.platform.orEmpty(),
           publisher = this.publisher.orEmpty(),
           developer = this.developer.orEmpty(),
           releaseDate = this.releaseDate?.substring(0, 4).orEmpty(),
           freetogameProfileURL = this.freetogameProfileUrl.orEmpty()
    )
}

fun List<RemoteGameListItem>.toUIModelList(): List<GameUIModel> {
    return this.map { it.toUIModel() }
}
