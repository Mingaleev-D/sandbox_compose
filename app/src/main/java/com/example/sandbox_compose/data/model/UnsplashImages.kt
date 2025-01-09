package com.example.sandbox_compose.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteUnsplashImagesItem(
       @SerialName("blur_hash")
       val blurHash: String = "",
       @SerialName("color")
       val color: String = "",
       @SerialName("created_at")
       val createdAt: String = "",
       @SerialName("current_user_collections")
       val currentUserCollections: List<RemoteCurrentUserCollection?> = listOf(),
       @SerialName("description")
       val description: String = "",
       @SerialName("height")
       val height: Int = 0,
       @SerialName("id")
       val id: String = "",
       @SerialName("liked_by_user")
       val likedByUser: Boolean = false,
       @SerialName("likes")
       val likes: Int = 0,
       @SerialName("links")
       val links: RemoteLinks = RemoteLinks(),
       @SerialName("updated_at")
       val updatedAt: String = "",
       @SerialName("urls")
       val urls: RemoteUrls = RemoteUrls(),
       @SerialName("user")
       val user: RemoteUser = RemoteUser(),
       @SerialName("width")
       val width: Int = 0
) {

    @Serializable
    data class RemoteCurrentUserCollection(
           @SerialName("id")
           val id: Int = 0,
           @SerialName("last_collected_at")
           val lastCollectedAt: String = "",
           @SerialName("published_at")
           val publishedAt: String = "",
           @SerialName("title")
           val title: String = "",
           @SerialName("updated_at")
           val updatedAt: String = "",
    )

    @Serializable
    data class RemoteLinks(
           @SerialName("download")
           val download: String = "",
           @SerialName("download_location")
           val downloadLocation: String = "",
           @SerialName("html")
           val html: String = "",
           @SerialName("self")
           val self: String = ""
    )

    @Serializable
    data class RemoteUrls(
           @SerialName("full")
           val full: String = "",
           @SerialName("raw")
           val raw: String = "",
           @SerialName("regular")
           val regular: String = "",
           @SerialName("small")
           val small: String = "",
           @SerialName("thumb")
           val thumb: String = ""
    )

    @Serializable
    data class RemoteUser(
           @SerialName("bio")
           val bio: String = "",
           @SerialName("id")
           val id: String = "",
           @SerialName("instagram_username")
           val instagramUsername: String = "",
           @SerialName("links")
           val links: RemoteLinks = RemoteLinks(),
           @SerialName("location")
           val location: String = "",
           @SerialName("name")
           val name: String = "",
           @SerialName("portfolio_url")
           val portfolioUrl: String = "",
           @SerialName("profile_image")
           val profileImage: RemoteProfileImage = RemoteProfileImage(),
           @SerialName("total_collections")
           val totalCollections: Int = 0,
           @SerialName("total_likes")
           val totalLikes: Int = 0,
           @SerialName("total_photos")
           val totalPhotos: Int = 0,
           @SerialName("twitter_username")
           val twitterUsername: String = "",
           @SerialName("username")
           val username: String = ""
    ) {

        @Serializable
        data class RemoteLinks(
               @SerialName("html")
               val html: String = "",
               @SerialName("likes")
               val likes: String = "",
               @SerialName("photos")
               val photos: String = "",
               @SerialName("portfolio")
               val portfolio: String = "",
               @SerialName("self")
               val self: String = ""
        )

        @Serializable
        data class RemoteProfileImage(
               @SerialName("large")
               val large: String = "",
               @SerialName("medium")
               val medium: String = "",
               @SerialName("small")
               val small: String = ""
        )
    }
}
