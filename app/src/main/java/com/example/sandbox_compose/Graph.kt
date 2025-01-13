package com.example.sandbox_compose

import android.content.Context
import com.example.sandbox_compose.data.repository.AuthRepository
import com.example.sandbox_compose.data.repository.AuthRepositoryImpl
import com.example.sandbox_compose.data.repository.GoogleAuthClient
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig
import com.google.android.gms.auth.api.identity.Identity

object Graph {

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl()
    }
    //    val chatRepository: ChatRepository by lazy {
    //        ChatRepository()
    //    }
    //    val photoReasoningRepository: PhotoReasoningRepository by lazy {
    //        PhotoReasoningRepository()
    //    }

        lateinit var googleAuthClient: GoogleAuthClient
        private val config = generationConfig {
            temperature = .7f
        }

    //    fun generativeModel(modelName: String) = GenerativeModel(
    //           modelName = modelName,
    //           apiKey = BuildConfig.apiKey,
    //           generationConfig = config
    //    )
    //
        fun provide(context: Context) {
            googleAuthClient = GoogleAuthClient(
                   oneTapClient = Identity.getSignInClient(context)
            )
        }
}
