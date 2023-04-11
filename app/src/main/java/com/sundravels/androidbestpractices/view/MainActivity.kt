package com.sundravels.androidbestpractices.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.uiresources.theme.AndroidBestPracticesTheme
import com.sundravels.androidbestpractices.ui.AbpApp
import com.sundravels.androidbestpractices.ui.AbpAppState
import com.sundravels.androidbestpractices.ui.rememberNiaAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //handle splash screen transition
        val splashScreen  = installSplashScreen()
        super.onCreate(savedInstanceState)
        
        setContent {
            AndroidBestPracticesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.surface
                ) {
                    AbpApp(rememberNiaAppState())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidBestPracticesTheme {
        Greeting("Android")
    }
}