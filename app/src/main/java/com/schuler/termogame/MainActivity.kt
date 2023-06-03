package com.schuler.termogame

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE
import com.google.android.ump.ConsentForm
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import com.schuler.termogame.components.PreConsentMessage
import com.schuler.termogame.navigation.AppNavigation
import com.schuler.termogame.screens.home.HomeViewModel
import com.schuler.termogame.ui.theme.TermoGameTheme
import dagger.hilt.android.AndroidEntryPoint

//Preference Name
const val PREFERENCE_NAME = "AppDataStore"

//Instance of DataStore
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var consentInformation: ConsentInformation
    private lateinit var consentForm: ConsentForm
    override fun onCreate(savedInstanceState: Bundle?) {

//        consentInformation.reset()
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                val homeViewModel: HomeViewModel by viewModels()
                AppNavigation(homeViewModel)
            }
        }

//        Para Testar consentimento:

//        val debugSettings = ConsentDebugSettings.Builder(this)
//            .setDebugGeography(ConsentDebugSettings
//                .DebugGeography
//                .DEBUG_GEOGRAPHY_EEA)
//            .addTestDeviceHashedId("289857E0A1E13FC4737C97D2F7627A14")
//            .build()
//
//        val params = ConsentRequestParameters
//            .Builder()
//            .setConsentDebugSettings(debugSettings)
//            .build()


        val params = ConsentRequestParameters
            .Builder()
            .setTagForUnderAgeOfConsent(false)
            .build()

        consentInformation = UserMessagingPlatform.getConsentInformation(this)
        consentInformation.requestConsentInfoUpdate(
            this,
            params,
            {
                // The consent information state was updated.
                // You are now ready to check if a form is available.
                if (consentInformation.isConsentFormAvailable) {
                    loadForm()
                }
            },
            {
                // Handle the error.
            })

//        consentInformation.reset()


    }
    private fun loadForm() {
        // Loads a consent form. Must be called on the main thread.
        UserMessagingPlatform.loadConsentForm(
            this,
            {consentForm ->
                this.consentForm = consentForm
                if (consentInformation.consentStatus == ConsentInformation.ConsentStatus.REQUIRED) {
                    setContent {
                        MyApp {
                            PreConsentMessage()
                        }
                    }
                    consentForm.show(this) {
                        if (consentInformation.consentStatus == ConsentInformation.ConsentStatus.OBTAINED) {
                            // App can start requesting ads.
                            MobileAds.initialize(this){
                                setContent {
                                    MyApp{
                                        val homeViewModel: HomeViewModel by viewModels()
                                        AppNavigation(homeViewModel)
                                    }
                                }
                            }
                            val conf = RequestConfiguration.Builder()
                                .setTagForChildDirectedTreatment(
                                    TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE
                                ).build()
                            MobileAds.setRequestConfiguration(conf)
                        }
                        // Handle dismissal by reloading form.
                        loadForm()
                    }
                }
            },
            {
                // Handle the error.
            }
        )
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    TermoGameTheme{
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp{
        AppNavigation()
    }
}