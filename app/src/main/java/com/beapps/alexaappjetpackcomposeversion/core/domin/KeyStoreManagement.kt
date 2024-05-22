package com.beapps.alexaappjetpackcomposeversion.core.domin

interface KeysStoreManagement {
    fun saveIfCommandsDbIsValidState (isValid : Boolean)
    fun saveIfCommandsDbIsSavedState (isSaved : Boolean)
    fun saveDeviceLocal (local : String)

    fun getIfCommandsDbIsValidState() : Boolean
    fun getIfCommandsDbIsSavedState() : Boolean
    fun getSavedDeviceLocal() : String
}