package com.example.sandbox_compose.data.repository

import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.example.sandbox_compose.utils.Constants
import com.example.sandbox_compose.utils.Resource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthClient(
       private val oneTapClient: SignInClient,
) {
    companion object {
        const val TAG = "google_auth"
    }

    suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(buildSignInRequest()).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    fun signInWithIntent(intent: Intent)
           : Flow<Resource<AuthResult?>> = callbackFlow {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(
               googleIdToken, null
        )
        try {
            trySend(Resource.Loading())
            Firebase.auth.signInWithCredential(googleCredentials)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(Resource.Success(it.result))
                    } else {
                        trySend(Resource.Error(it.exception))
                    }
                }.await()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        awaitClose { }

    }


    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                   GoogleIdTokenRequestOptions.builder()
                       .setSupported(true)
                       .setFilterByAuthorizedAccounts(false)
                       .setServerClientId(Constants.WEB_CLIENT)
                       .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }


}
