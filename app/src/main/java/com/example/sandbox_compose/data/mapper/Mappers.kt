package com.example.sandbox_compose.data.mapper

import com.example.sandbox_compose.data.local.FavoriteImageEntity
import com.example.sandbox_compose.data.model.RemoteUnsplashImagesItem
import com.example.sandbox_compose.domain.model.UnsplashImage

fun RemoteUnsplashImagesItem.toDomainModel(): UnsplashImage {
    return UnsplashImage(
           id = this.id,
           imageUrlSmall = this.urls.small,
           imageUrlRegular = this.urls.regular,
           imageUrlRaw = this.urls.raw,
           photographerName = this.user.name,
           photographerUsername = this.user.username,
           photographerProfileImgUrl = this.user.profileImage.small,
           photographerProfileLink = this.user.links.html,
           width = this.width,
           height = this.height,
           description = description
    )
}

fun List<RemoteUnsplashImagesItem>.toDomainModelList(): List<UnsplashImage> {
    return this.map { it.toDomainModel() }
}

fun UnsplashImage.toFavoriteImageEntity(): FavoriteImageEntity {
    return FavoriteImageEntity(
           id = this.id,
           imageUrlSmall = this.imageUrlSmall,
           imageUrlRegular = this.imageUrlRegular,
           imageUrlRaw = this.imageUrlRaw,
           photographerName = this.photographerName,
           photographerUsername = this.photographerUsername,
           photographerProfileImgUrl = this.photographerProfileImgUrl,
           photographerProfileLink = this.photographerProfileLink,
           width = this.width,
           height = this.height,
           description = description
    )
}

fun FavoriteImageEntity.toDomainModel(): UnsplashImage {
    return UnsplashImage(
           id = this.id,
           imageUrlSmall = this.imageUrlSmall,
           imageUrlRegular = this.imageUrlRegular,
           imageUrlRaw = this.imageUrlRaw,
           photographerName = this.photographerName,
           photographerUsername = this.photographerUsername,
           photographerProfileImgUrl = this.photographerProfileImgUrl,
           photographerProfileLink = this.photographerProfileLink,
           width = this.width,
           height = this.height,
           description = description
    )
}
