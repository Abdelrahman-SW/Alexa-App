package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain

import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.nl.translate.TranslateRemoteModel
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class TranslatorImpl : Translator {

    override fun translate(
        text: String,
        sourceLanguage: SupportedLanguages,
        targetLanguage: SupportedLanguages
    ): Flow<TranslateResult> {
        return callbackFlow {
            val translatorOptions = TranslatorOptions.Builder()
                .setSourceLanguage(sourceLanguage.tag)
                .setTargetLanguage(targetLanguage.tag)
                .build()
            val translator = Translation.getClient(translatorOptions);
            val remoteModel = TranslateRemoteModel.Builder(sourceLanguage.tag).build()
            RemoteModelManager.getInstance().isModelDownloaded(remoteModel).addOnSuccessListener {
                if (it) {
                    translator.translate(text)
                        .addOnSuccessListener { result ->
                            if (result.isNullOrBlank()) {
                                trySend(
                                    TranslateResult.Error(
                                        TranslationErrors.EmptyResult
                                    )
                                )
                            } else {
                                trySend(TranslateResult.Success(result))
                            }
                            close()
                        }
                        .addOnFailureListener { e ->
                            trySend(TranslateResult.Error(TranslationErrors.Others(e)))
                            close()
                        }
                } else {
                    trySend(TranslateResult.Error(TranslationErrors.ModelNotFound))
                    close()
                }
            }

            awaitClose {
                translator.close()
            }
        }
    }


}