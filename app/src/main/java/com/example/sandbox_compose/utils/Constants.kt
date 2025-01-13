package com.example.sandbox_compose.utils

import android.util.Log
import kotlinx.coroutines.flow.Flow
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.Patterns
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Constants {

    const val API_KEY = "AIzaSyA_OTVieiFMwTHpsp9l9rVpqbddtRsFSZc"
    const val WEB_CLIENT = "1017095532087-dmh9ocms40jek22upi6e4plh6g4cipfj.apps.googleusercontent.com"
}

fun isValidEmail(email: String): Boolean {
    if (email.isEmpty()) return false
    val emailPattern = Patterns.EMAIL_ADDRESS
    return emailPattern.matcher(email).matches()
}

fun formatDate(date: Date): String {
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(date)
}

fun saveImageToFileAndGetUri(
       bitmap: Bitmap,
       context: Context
): Uri? {
    val fileName = "${System.currentTimeMillis()}.jpg"
    val imagesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val imageFile = File(imagesDir, fileName)
    var fos: OutputStream? = null
    try {
        fos = FileOutputStream(imageFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        fos.flush()
        return Uri.fromFile(imageFile)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        fos?.close()
    }
    return null
}

suspend fun <T> Flow<Resource<T>>.collectAndHandle(
       onError: (Throwable?) -> Unit = {
           Log.e("collectAndHandle", "collectAndHandle: error", it)
       },
       onLoading: () -> Unit = {},
       stateReducer: (T) -> Unit,
) {
    collect { response ->
        when (response) {
            is Resource.Error -> {
                onError(response.error)
            }

            is Resource.Success -> {
                stateReducer(response.dataSuccess)
            }

            is Resource.Loading -> {
                onLoading()
            }
        }
    }
}
