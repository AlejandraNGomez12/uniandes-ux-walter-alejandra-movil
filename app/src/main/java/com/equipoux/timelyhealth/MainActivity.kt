package com.equipoUX.timelyhealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.equipoUX.timelyhealth.view.screens.StartScreen
import com.equipoUX.timelyhealth.view.screens.TimelyHealthApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "Start") {
                composable(route = "Start") {
                    StartScreen(navController = navController)
                }
                composable(route = "Welcome") {
                    TimelyHealthApp()
                }
            }

        }


    }
}

@Preview(showBackground = true)
@Composable
fun TimelyPreview() {
    StartScreen(rememberNavController())
}