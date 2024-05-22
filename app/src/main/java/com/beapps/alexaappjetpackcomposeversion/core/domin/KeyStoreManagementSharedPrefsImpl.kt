package com.beapps.alexaappjetpackcomposeversion.core.domin

import android.content.Context
import android.content.SharedPreferences


class KeyStoreManagementSharedPrefsImpl(
    val context: Context
): KeysStoreManagement {

    private val sharedPrefsName = "AlexApp_Prefs"
    private val sharedPrefsDeviceLocaleTagKey = "local_tag"
    private val sharedPrefsIfCommandsDbIsValidKey = "dbValidation"
    private val sharedPrefsIsCommandsSavedKey = "isCommandsSaved"

    private val sharedPrefs: SharedPreferences by lazy {
        context.getSharedPreferences(
            sharedPrefsName,
            Context.MODE_PRIVATE
        )
    }
    private val sharedEditor: SharedPreferences.Editor by lazy { sharedPrefs.edit() }
    override fun saveIfCommandsDbIsValidState(isValid: Boolean) {
        sharedEditor.putBoolean(sharedPrefsIfCommandsDbIsValidKey, isValid).commit()
    }

    override fun saveIfCommandsDbIsSavedState(isSaved: Boolean) {
        sharedEditor.putBoolean(sharedPrefsIsCommandsSavedKey, isSaved).commit()
    }

    override fun saveDeviceLocal(local: String) {
        sharedEditor.putString(sharedPrefsDeviceLocaleTagKey, local).commit()
    }

    override fun getIfCommandsDbIsValidState(): Boolean {
        return sharedPrefs.getBoolean(sharedPrefsIfCommandsDbIsValidKey, true)
    }

    override fun getIfCommandsDbIsSavedState(): Boolean {
        return sharedPrefs.getBoolean(sharedPrefsIsCommandsSavedKey, false)

    }

    override fun getSavedDeviceLocal(): String {
        return sharedPrefs.getString(sharedPrefsDeviceLocaleTagKey , "") ?: ""
    }

}