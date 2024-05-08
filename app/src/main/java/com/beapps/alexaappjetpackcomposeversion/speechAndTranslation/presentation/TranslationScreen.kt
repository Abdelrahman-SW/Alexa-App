package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.presentation

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beapps.alexaappjetpackcomposeversion.core.domin.isDeviceConnectedToWifi
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.TranslationErrors
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.languages
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.presentation.components.LanguageDialog

@Composable
fun TranslationScreen(modifier: Modifier = Modifier) {
    val translationViewModel = hiltViewModel<TranslationViewModel>()
    val screenState = translationViewModel.screenState
    val context = LocalContext.current
    val permLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            translationViewModel.handleAudioPermResult(it)
        }
    )
    LaunchedEffect(key1 = true) {
        permLauncher.launch(android.Manifest.permission.RECORD_AUDIO)
    }

    LaunchedEffect(key1 = screenState.isRecordAudioPermGranted) {
        if (screenState.isRecordAudioPermGranted) translationViewModel.init()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = {
            if (screenState.isRecordAudioPermGranted) {
                if (screenState.selectedLanguage != null)
                    translationViewModel.onMicClicked()
                else {
                    Toast.makeText(context, "Please Select Language First", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                permLauncher.launch(android.Manifest.permission.RECORD_AUDIO)
            }
        }) {
            Icon(
                imageVector = when (val state = screenState.speechRecognizerState) {
                    is SpeechRecognizerState.Error -> Icons.Default.Mic
                    SpeechRecognizerState.Listening -> Icons.Default.StopCircle
                    SpeechRecognizerState.Ready -> Icons.Default.Mic
                }, contentDescription = "Record"
            )
        }



        Spacer(modifier = Modifier.height(32.dp))


        Text(
            text = when (val state = screenState.speechRecognizerState) {
                is SpeechRecognizerState.Error -> state.error.name
                SpeechRecognizerState.Listening -> "Listening"
                SpeechRecognizerState.Ready -> "Ready"
            },
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )


        Spacer(modifier = Modifier.height(32.dp))


        Text(
            text = screenState.speechRecognizerResult,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))



        Button(onClick = {
            if (screenState.speechRecognizerResult.isNotEmpty()) {
                translationViewModel.onTranslateBtnClicked()
            } else {
                Toast.makeText(context, "Please Speak First", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "Translate")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = when (val state = screenState.translationState) {
                is TranslationState.Error -> when (state.error) {
                    TranslationErrors.EmptyResult -> "Empty Result"
                    TranslationErrors.ModelNotFound -> {
                        if (context.isDeviceConnectedToWifi()) {
                            "Please Wait The Model Is Downloading"
                        } else {
                            "Model Not Downloaded, Please Connect to Wifi To Continue Downloading"
                        }
                    }

                    is TranslationErrors.Others -> state.error.e.message ?: "No message"
                }

                TranslationState.Idle -> "Ready To Translate"
            },
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = screenState.translationResult,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )


        Button(onClick = {
            translationViewModel.onLanguageBtnClicked()
        }) {
            Text(text = screenState.selectedLanguage?.name ?: "Show Languages")
        }

        if (screenState.openLanguageDialog) {
            LanguageDialog(
                languages = languages,
                onItemClick = {
                    translationViewModel.onSelectedLanguage(it)
                    translationViewModel.onLanguageDialogDismiss()
                },
                onDismiss = {
                    translationViewModel.onLanguageDialogDismiss()
                }
            )
        }


    }
}

