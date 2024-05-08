package com.beapps.alexaappjetpackcomposeversion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beapps.alexaappjetpackcomposeversion.core.domin.TranslatorDownloader

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val translatorDownloader: TranslatorDownloader
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
}