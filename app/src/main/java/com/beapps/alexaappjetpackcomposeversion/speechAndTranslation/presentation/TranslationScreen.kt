package com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.presentation

import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.beapps.alexaappjetpackcomposeversion.core.domin.isDeviceConnectedToWifi
import com.beapps.alexaappjetpackcomposeversion.core.domin.shareText
import com.beapps.alexaappjetpackcomposeversion.core.presentation.poppinsFontFamily
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.TranslationErrors
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.domain.languages
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.presentation.components.LanguageDialog
import com.beapps.alexaappjetpackcomposeversion.speechAndTranslation.presentation.components.TranslationResultItem
import com.beapps.alexaappjetpackcomposeversion.ui.theme.mainComponentColor
import com.beapps.alexaappjetpackcomposeversion.ui.theme.mainComponentColor35Alpha

@OptIn(ExperimentalMaterial3Api::class)
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
        permLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    LaunchedEffect(key1 = screenState.isRecordAudioPermGranted) {
        if (screenState.isRecordAudioPermGranted) translationViewModel.init()
    }


    Scaffold(modifier = modifier
        .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Translation",
                        color = Color.White,
                        fontFamily = poppinsFontFamily,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = mainComponentColor),
            )
        }
    )
    { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = mainComponentColor35Alpha)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Button(
                        onClick = {
                            translationViewModel.onLanguageBtnClicked()
                        }, enabled = screenState.speechRecognizerState
                                !is SpeechRecognizerState.Listening
                    ) {
                        Text(
                            text = screenState.selectedLanguage?.name ?: "Select Language",
                            fontFamily = poppinsFontFamily
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = when (val state = screenState.speechRecognizerState) {
                            is SpeechRecognizerState.Error -> state.error.name
                            SpeechRecognizerState.Listening -> "Listening . . ."
                            SpeechRecognizerState.Ready -> "Tap On Mic And Start Speaking .. "
                        },
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 13.sp,
                        fontFamily = poppinsFontFamily
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        Modifier
                            .verticalScroll(rememberScrollState())
                            .heightIn(max = 100.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = screenState.speechRecognizerResult,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = poppinsFontFamily,
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(modifier = Modifier
                            .clip(CircleShape)
                            .background(mainComponentColor)
                            .clickable {
                                if (screenState.isRecordAudioPermGranted) {
                                    if (screenState.selectedLanguage != null)
                                        translationViewModel.onMicClicked()
                                    else {
                                        Toast
                                            .makeText(
                                                context,
                                                "Please Select Language First",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                } else {
                                    permLauncher.launch(Manifest.permission.RECORD_AUDIO)
                                }
                            }
                            .padding(6.dp)) {
                            Icon(
                                imageVector = when (screenState.speechRecognizerState) {
                                    is SpeechRecognizerState.Error -> Icons.Default.Mic
                                    SpeechRecognizerState.Listening -> Icons.Default.StopCircle
                                    SpeechRecognizerState.Ready -> Icons.Default.Mic
                                }, contentDescription = "Record",
                                Modifier.size(32.dp)
                            )
                        }

                        Button(
                            onClick = {
                                if (screenState.speechRecognizerResult.isNotEmpty()) {
                                    translationViewModel.onTranslateBtnClicked()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please Speak First",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }, enabled = screenState.speechRecognizerState
                                    !is SpeechRecognizerState.Listening
                        ) {
                            Text(text = "Translate", fontFamily = poppinsFontFamily)
                        }
                    }


                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (screenState.translationState != TranslationState.Ready) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
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

                            is TranslationErrors.Others -> state.error.e.message ?: "Undefined Error"
                        }

                        TranslationState.Ready -> ""
                        TranslationState.Translating -> "Translating ..."
                    },
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily
                )

                Spacer(modifier = Modifier.height(8.dp))

            }

            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = "Translation Results :",
                fontSize = 18.sp,
                fontFamily = poppinsFontFamily
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(screenState.translationResults) {
                    TranslationResultItem(
                        translateResult = it,
                        onItemClicked = { text ->
                            translationViewModel.onTranslateResultItemClicked(text)
                        },
                        onShareClick = { text2 ->
                            context.shareText(text2)
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

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


