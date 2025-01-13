package com.example.sandbox_compose.data.repository

import com.example.sandbox_compose.utils.Resource
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.CancellationException

class AuthRepositoryImpl : AuthRepository {

    override val currentUser: MutableStateFlow<FirebaseUser?>
        get() = MutableStateFlow(Firebase.auth.currentUser)

    override fun hasVerifiedUser(): Boolean {
        return Firebase.auth.currentUser?.isEmailVerified ?: false
    }

    override fun hasUser(): Boolean {
        return Firebase.auth.currentUser != null
    }

    override fun getUserId(): String {
        return Firebase.auth.currentUser?.uid.orEmpty()
    }

    override fun sendEmailVerification(
           onSuccess: () -> Unit,
           onError: (error: Throwable?) -> Unit
    ) {
        Firebase.auth.currentUser
            ?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.cause)
                }
            }
    }

    override suspend fun login(
           email: String,
           password: String
    ): Flow<Resource<AuthResult?>> =
           callbackFlow {
               try {
                   trySend(Resource.Loading())
                   Firebase.auth
                       .signInWithEmailAndPassword(email, password)
                       .addOnCompleteListener {
                           currentUser.value = it.result.user
                           if (it.isSuccessful) {
                               trySend(Resource.Success(it.result))
                           } else {
                               trySend(Resource.Error(it.exception))
                           }
                       }
               } catch (e: Exception) {
                   trySend(Resource.Error(e))
               }
               awaitClose { }
           }

    override suspend fun createUser(
           email: String,
           password: String
    ): Flow<Resource<AuthResult?>> =
           callbackFlow {
               try {
                   trySend(Resource.Loading())
                   Firebase.auth
                       .createUserWithEmailAndPassword(email, password)
                       .addOnCompleteListener {
                           if (it.isSuccessful) {
                               currentUser.value = it.result.user
                               trySend(Resource.Success(it.result))
                           } else {
                               trySend(Resource.Error(it.exception))
                           }
                       }
               } catch (e: Exception) {
                   trySend(Resource.Error(e))
                   if (e is CancellationException) throw e
               }
               awaitClose { }
           }

    override suspend fun signInWithCredentials(credential: AuthCredential): Flow<Resource<AuthResult?>> = callbackFlow {
        try {
            trySend(Resource.Loading())
            Firebase.auth.signInWithCredential(credential)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(Resource.Success(it.result))
                    } else {
                        trySend(Resource.Error(it.exception))
                    }
                }
        } catch (e: Exception) {
            trySend(Resource.Error(e))
        }
    }

    override suspend fun sendPasswordResetLink(email: String): Flow<Resource<Boolean>> = callbackFlow {
        try {
            trySend(Resource.Loading())
            Firebase.auth.sendPasswordResetEmail(email)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        trySend(Resource.Success(it.isSuccessful))
                    } else {
                        trySend(Resource.Error(it.exception))
                    }
                }
        } catch (e: Exception) {
            trySend(Resource.Error(e))
        }
    }

    override fun signOut() {
        Firebase.auth.signOut()
        currentUser.value = null
    }
}

interface AuthRepository {

    val currentUser: MutableStateFlow<FirebaseUser?>
    fun hasVerifiedUser(): Boolean
    fun hasUser(): Boolean
    fun getUserId(): String

    fun sendEmailVerification(
           onSuccess: () -> Unit,
           onError: (error: Throwable?) -> Unit
    )

    suspend fun login(
           email: String,
           password: String
    ): Flow<Resource<AuthResult?>>

    suspend fun createUser(
           email: String,
           password: String
    ): Flow<Resource<AuthResult?>>

    suspend fun signInWithCredentials(credential: AuthCredential): Flow<Resource<AuthResult?>>

    suspend fun sendPasswordResetLink(email: String): Flow<Resource<Boolean>>

    fun signOut()
}
