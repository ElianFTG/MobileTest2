package com.progra.parcialdos


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.progra.parcialdos.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint
import com.progra.parcialdos.ui.theme.ParcialdossssTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Parcialdos)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParcialdossssTheme {
                AppNavigation()
            }

        }
    }
}

