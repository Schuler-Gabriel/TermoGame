package com.schuler.termogame.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdaptiveBanner(
    adId: String,
    modifier: Modifier = Modifier,
) {

    val deviceCurrentWidth = LocalConfiguration.current.screenWidthDp
    val applicationContext = LocalContext.current.applicationContext

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background),
    ) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = {
                // Using application context to avoid memory leak
                AdView(applicationContext).apply {
                    setAdSize(
                        AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                            applicationContext,
                            deviceCurrentWidth,
                        ),
                    )
                    adUnitId = adId
                    loadAd(AdRequest.Builder().build())
                }
            },
        )
    }
}