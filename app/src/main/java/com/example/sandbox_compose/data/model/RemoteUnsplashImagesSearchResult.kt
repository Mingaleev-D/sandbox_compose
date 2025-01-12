package com.example.sandbox_compose.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteUnsplashImagesSearchResult(
    @SerialName("results")
    val results: List<RemoteUnsplashImagesItem?> = listOf(),
    @SerialName("total")
    val total: Int = 0,
    @SerialName("total_pages")
    val totalPages: Int = 0
) {
//    @Serializable
//    data class RemoteResult(
//        @SerialName("blur_hash")
//        val blurHash: String = "",
//        @SerialName("color")
//        val color: String = "",
//        @SerialName("created_at")
//        val createdAt: String = "",
//        @SerialName("description")
//        val description: String = "",
//        @SerialName("height")
//        val height: Int = 0,
//        @SerialName("id")
//        val id: String = "",
//        @SerialName("liked_by_user")
//        val likedByUser: Boolean = false,
//        @SerialName("likes")
//        val likes: Int = 0,
//        @SerialName("links")
//        val links: RemoteLinks = RemoteLinks(),
//        @SerialName("urls")
//        val urls: RemoteUrls = RemoteUrls(),
//        @SerialName("user")
//        val user: RemoteUser = RemoteUser(),
//        @SerialName("width")
//        val width: Int = 0
//    ) {
//        @Serializable
//        data class RemoteLinks(
//            @SerialName("download")
//            val download: String = "",
//            @SerialName("html")
//            val html: String = "",
//            @SerialName("self")
//            val self: String = ""
//        )
//
//        @Serializable
//        data class RemoteUrls(
//            @SerialName("full")
//            val full: String = "",
//            @SerialName("raw")
//            val raw: String = "",
//            @SerialName("regular")
//            val regular: String = "",
//            @SerialName("small")
//            val small: String = "",
//            @SerialName("thumb")
//            val thumb: String = ""
//        )
//
//        @Serializable
//        data class RemoteUser(
//            @SerialName("first_name")
//            val firstName: String = "",
//            @SerialName("id")
//            val id: String = "",
//            @SerialName("instagram_username")
//            val instagramUsername: String = "",
//            @SerialName("last_name")
//            val lastName: String = "",
//            @SerialName("links")
//            val links: RemoteLinks = RemoteLinks(),
//            @SerialName("name")
//            val name: String = "",
//            @SerialName("portfolio_url")
//            val portfolioUrl: String = "",
//            @SerialName("profile_image")
//            val profileImage: RemoteProfileImage = RemoteProfileImage(),
//            @SerialName("twitter_username")
//            val twitterUsername: String = "",
//            @SerialName("username")
//            val username: String = ""
//        ) {
//            @Serializable
//            data class RemoteLinks(
//                @SerialName("html")
//                val html: String = "",
//                @SerialName("likes")
//                val likes: String = "",
//                @SerialName("photos")
//                val photos: String = "",
//                @SerialName("self")
//                val self: String = ""
//            )
//
//            @Serializable
//            data class RemoteProfileImage(
//                @SerialName("large")
//                val large: String = "",
//                @SerialName("medium")
//                val medium: String = "",
//                @SerialName("small")
//                val small: String = ""
//            )
//        }
//    }
}
