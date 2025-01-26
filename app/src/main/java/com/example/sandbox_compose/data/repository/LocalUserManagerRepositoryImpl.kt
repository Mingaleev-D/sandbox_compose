package com.example.sandbox_compose.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.sandbox_compose.domain.repository.LocalUserManagerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

object Constants {

    const val USER_SETTINGS = "user_settings"
    const val APP_ENTRY = "app_entry"

}

class LocalUserManagerRepositoryImpl @Inject constructor(
       private val context: Context
) : LocalUserManagerRepository {

    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)
private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}
