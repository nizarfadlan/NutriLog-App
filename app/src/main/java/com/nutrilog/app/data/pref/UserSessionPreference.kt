package com.nutrilog.app.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.nutrilog.app.domain.model.Gender
import com.nutrilog.app.domain.model.User
import com.nutrilog.app.utils.constant.AppConstant.PREFS_SESSIONS
import com.nutrilog.app.utils.helpers.convertGender
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStoreSession: DataStore<Preferences> by preferencesDataStore(name = PREFS_SESSIONS)

class UserSessionPreference(context: Context) {
    private val dataStore = context.dataStoreSession

    private val idKey = stringPreferencesKey("id")
    private val nameKey = stringPreferencesKey("name")
    private val emailKey = stringPreferencesKey("email")
    private val genderKey = stringPreferencesKey("gender")
    private val ageKey = intPreferencesKey("age")
    private val tokenKey = stringPreferencesKey("token")

    suspend fun saveSession(user: User) {
        dataStore.edit { preferences ->
            preferences[idKey] = user.id
            preferences[nameKey] = user.name
            preferences[emailKey] = user.email
            preferences[genderKey] = user.gender.label
            preferences[ageKey] = user.age
            preferences[tokenKey] = user.token
        }
    }

    fun getSession(): Flow<User> {
        return dataStore.data.map { preferences ->
            User(
                preferences[idKey] ?: "",
                preferences[nameKey] ?: "",
                preferences[emailKey] ?: "",
                preferences[genderKey]?.convertGender() ?: Gender.OTHER,
                preferences[ageKey] ?: 0,
                preferences[tokenKey] ?: "",
            )
        }
    }

    suspend fun getToken(): String = dataStore.data.first()[tokenKey] ?: ""

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
