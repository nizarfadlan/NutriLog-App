package com.nutrilog.app.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.nutrilog.app.domain.model.ActiveLevel
import com.nutrilog.app.domain.model.Language
import com.nutrilog.app.utils.constant.AppConstant.PREFS_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStoreSetting: DataStore<Preferences> by preferencesDataStore(name = PREFS_SETTINGS)

class SettingPreference(context: Context) {
    private val dataStore = context.dataStoreSetting
    private val languageKey = stringPreferencesKey("language_setting")
    private val activeLevelKey = stringPreferencesKey("level_setting")

    fun getLanguageSetting(): Flow<Language> {
        return dataStore.data.map { preferences ->
            val languageString = preferences[languageKey] ?: Language.ENGLISH.name
            Language.valueOf(languageString)
        }
    }

    suspend fun saveLanguageSetting(language: Language) {
        dataStore.edit { preferences ->
            preferences[languageKey] = language.name
        }
    }

    fun getActiveLevelSetting(): Flow<ActiveLevel> {
        return dataStore.data.map { preferences ->
            val levelString = preferences[activeLevelKey] ?: ActiveLevel.ACTIVE.name
            ActiveLevel.valueOf(levelString)
        }
    }

    suspend fun setActiveLevelSetting(level: ActiveLevel) {
        dataStore.edit { preferences ->
            preferences[activeLevelKey] = level.name
        }
    }
}
