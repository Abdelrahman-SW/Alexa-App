package com.beapps.alexaappjetpackcomposeversion.core.domin

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.TranslateRemoteModel
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

class TranslatorDownloaderMlKitImpl : TranslatorDownloader {
    override fun downloadAllModels() {

        val modelManager = RemoteModelManager.getInstance()

        lateinit var translatorArabic: Translator
        lateinit var translatorEnglish: Translator
        lateinit var translatorFrench: Translator

        lateinit var translatorGerman: Translator

        lateinit var translatorIndonesian: Translator

        lateinit var translatorJapanese: Translator

        lateinit var translatorPortuguese: Translator

        lateinit var translatorRussian: Translator

        lateinit var translatorChinese: Translator

        lateinit var translatorSpanish: Translator


        val downloadConditions = DownloadConditions.Builder()
            .requireWifi()
            .build()


        val translatorOptionsArabic = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ARABIC)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()

        val translatorOptionsFrench = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.FRENCH)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()

        val translatorOptionsGerman = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.GERMAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()

        val translatorOptionsIndonesian = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.INDONESIAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()

        val translatorOptionsJapanese = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.JAPANESE)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()

        val translatorOptionsPortuguese = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.PORTUGUESE)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()


        val translatorOptionsRussian = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.RUSSIAN)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()

        val translatorOptionsChinese = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.CHINESE)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()

        val translatorOptionsSpanish = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.SPANISH)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()


        val translatorOptionsEnglish = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()



        translatorArabic = Translation.getClient(translatorOptionsArabic);
        translatorEnglish = Translation.getClient(translatorOptionsEnglish);
        translatorFrench = Translation.getClient(translatorOptionsFrench);
        translatorGerman = Translation.getClient(translatorOptionsGerman);
        translatorIndonesian = Translation.getClient(translatorOptionsIndonesian);
        translatorJapanese = Translation.getClient(translatorOptionsJapanese);
        translatorPortuguese = Translation.getClient(translatorOptionsPortuguese);
        translatorRussian = Translation.getClient(translatorOptionsRussian);
        translatorChinese = Translation.getClient(translatorOptionsChinese);
        translatorSpanish = Translation.getClient(translatorOptionsSpanish);

        translatorArabic.downloadModelIfNeeded(downloadConditions).addOnFailureListener {
            val arabicModel = TranslateRemoteModel.Builder(TranslateLanguage.ARABIC).build()
            modelManager.deleteDownloadedModel(arabicModel)
            modelManager.download(arabicModel, downloadConditions)
        }

        translatorEnglish.downloadModelIfNeeded(downloadConditions)
            .addOnFailureListener {
                val englishModel = TranslateRemoteModel.Builder(TranslateLanguage.ENGLISH).build()
                modelManager.deleteDownloadedModel(englishModel)
                modelManager.download(englishModel, downloadConditions)

            }

        translatorFrench.downloadModelIfNeeded(downloadConditions)
            .addOnFailureListener {
                val frenchModel = TranslateRemoteModel.Builder(TranslateLanguage.FRENCH).build()
                modelManager.deleteDownloadedModel(frenchModel)
                modelManager.download(frenchModel, downloadConditions)

            }

        translatorGerman.downloadModelIfNeeded(downloadConditions)
            .addOnFailureListener {
                val germanModel = TranslateRemoteModel.Builder(TranslateLanguage.GERMAN).build()
                modelManager.deleteDownloadedModel(germanModel)
                modelManager.download(germanModel, downloadConditions)
            }

        translatorIndonesian.downloadModelIfNeeded(downloadConditions)
            .addOnFailureListener {
                val indonesianModel =
                    TranslateRemoteModel.Builder(TranslateLanguage.INDONESIAN).build()
                modelManager.deleteDownloadedModel(indonesianModel)
                modelManager.download(indonesianModel, downloadConditions)

            }

        translatorJapanese.downloadModelIfNeeded(downloadConditions)
            .addOnFailureListener {
                val japaneseModel = TranslateRemoteModel.Builder(TranslateLanguage.JAPANESE).build()
                modelManager.deleteDownloadedModel(japaneseModel)
                modelManager.download(japaneseModel, downloadConditions)

            }

        translatorPortuguese.downloadModelIfNeeded(downloadConditions)
            .addOnFailureListener {
                val portugueseModel =
                    TranslateRemoteModel.Builder(TranslateLanguage.PORTUGUESE).build()
                modelManager.deleteDownloadedModel(portugueseModel)
                modelManager.download(portugueseModel, downloadConditions)

            }

        translatorRussian.downloadModelIfNeeded(downloadConditions)
            .addOnFailureListener {
                val russianModel = TranslateRemoteModel.Builder(TranslateLanguage.RUSSIAN).build()
                modelManager.deleteDownloadedModel(russianModel)
                modelManager.download(russianModel, downloadConditions)

            }

        translatorChinese.downloadModelIfNeeded(downloadConditions)
            .addOnFailureListener {
                val chineseModel = TranslateRemoteModel.Builder(TranslateLanguage.CHINESE).build()
                modelManager.deleteDownloadedModel(chineseModel)
                modelManager.download(chineseModel, downloadConditions)

            }

        translatorSpanish.downloadModelIfNeeded(downloadConditions)
            .addOnFailureListener {
                val spanishModel = TranslateRemoteModel.Builder(TranslateLanguage.SPANISH).build()
                modelManager.deleteDownloadedModel(spanishModel)
                modelManager.download(spanishModel, downloadConditions)

            }

    }
}