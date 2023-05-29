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
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE
import com.schuler.termogame.components.AdaptiveBanner
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                val homeViewModel: HomeViewModel by viewModels()
                AppNavigation(homeViewModel)
            }
        }
        MobileAds.initialize(this)
        val conf = RequestConfiguration.Builder()
            .setTagForChildDirectedTreatment(TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE).build()
        MobileAds.setRequestConfiguration(conf)
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