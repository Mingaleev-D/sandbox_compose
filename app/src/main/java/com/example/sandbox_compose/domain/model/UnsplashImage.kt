package com.example.sandbox_compose.domain.model

data class UnsplashImage(
       val id: String,
       val imageUrlSmall: String,
       val imageUrlRegular: String,
       val imageUrlRaw: String,
       val photographerName: String,
       val photographerUsername: String,
       val photographerProfileImgUrl: String,
       val photographerProfileLink: String,
       val width: Int,
       val height: Int,
       val description: String?
)

//val sampleImage = UnsplashImage(
//       id = "LBI7cgq3pbM",
//       imageUrlSmall = "https://images.unsplash.com/photo-1721332153289-0c46dc38981b?ixid=M3w2OTQ5ODN8MXwxfGFsbHwxfHx8fHx8fHwxNzM2NDE2NDI1fA&ixlib=rb-4.0.3",
//       imageUrlRegular = "https://images.unsplash.com/photo-1721332153289-0c46dc38981b?ixid=M3w2OTQ5ODN8MXwxfGFsbHwxfHx8fHx8fHwxNzM2NDE2NDI1fA&ixlib=rb-4.0.3",
//       imageUrlRaw = "https://images.unsplash.com/photo-1721332153289-0c46dc38981b?ixid=M3w2OTQ5ODN8MXwxfGFsbHwxfHx8fHx8fHwxNzM2NDE2NDI1fA&ixlib=rb-4.0.3",
//       photographerName = "Gilbert Kane",
//       photographerUsername = "poorkane",
//       photographerProfileImgUrl = "https://images.unsplash.com/photo-1721332153289-0c46dc38981b?ixid=M3w2OTQ5ODN8MXwxfGFsbHwxfHx8fHx8fHwxNzM2NDE2NDI1fA&ixlib=rb-4.0.3",
//       photographerProfileLink = "https://unsplash.com/poorkane",
//       width = 5245,
//       height = 3497,
//       description = "A man drinking a coffee."
//)
