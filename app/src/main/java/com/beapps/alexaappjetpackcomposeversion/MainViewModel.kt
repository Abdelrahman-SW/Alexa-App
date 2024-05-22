package com.beapps.alexaappjetpackcomposeversion

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beapps.alexaappjetpackcomposeversion.core.domin.DeviceLocalProvider
import com.beapps.alexaappjetpackcomposeversion.core.domin.KeysStoreManagement
import com.beapps.alexaappjetpackcomposeversion.core.domin.TranslatorDownloader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val translatorDownloader: TranslatorDownloader,
    private val keysStoreManagement: KeysStoreManagement,
    private val deviceLocalProvider: DeviceLocalProvider
) : ViewModel() {

    private var isModelsDownloading = false
    fun downloadAllModels() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (isModelsDownloading) return@withContext
                isModelsDownloading = true
                translatorDownloader.downloadAllModels()
            }
        }
    }

    fun checkIfDeviceLocalChanged(onChanged : () -> Unit) {
        val isChanged: Boolean
        // compare it with the old saved locale
        val savedTag = keysStoreManagement.getSavedDeviceLocal()
        val tag = deviceLocalProvider.getCurrentDeviceLocalTag()
        isChanged = if (savedTag == "") {
            // for first time user opens the app
            keysStoreManagement.saveDeviceLocal(tag)
            false
        }
        else {
            tag != savedTag
        }
        if (isChanged) {
            keysStoreManagement.saveDeviceLocal(tag)
            keysStoreManagement.saveIfCommandsDbIsValidState(false)
            onChanged()
        }
    }
}