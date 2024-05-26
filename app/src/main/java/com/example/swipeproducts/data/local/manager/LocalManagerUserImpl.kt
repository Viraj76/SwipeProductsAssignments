package com.example.swipeproducts.data.local.manager


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.swipeproducts.data.local.manager.LocalUserManagerImpl.PreferencesKeys.APP_ENTRY
import com.example.swipeproducts.domain.local.manager.LocalUserManager
import com.example.swipeproducts.utils.Constants
import com.example.swipeproducts.utils.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LocalUserManagerImpl(
    private val context : Context
) : LocalUserManager {

    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

    private object PreferencesKeys{
        val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
    }

    override suspend fun saveUserEntry() {
        context.dataStore.edit {userSettings->
            userSettings[APP_ENTRY] = true
        }
    }


    override fun readUserEntry(): Flow<Boolean> {
        return context.dataStore.data.map {pref->
            pref[APP_ENTRY] ?: false
        }
    }



}