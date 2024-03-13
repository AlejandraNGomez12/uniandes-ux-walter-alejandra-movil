package com.equipoux.timelyhealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.equipoux.timelyhealth.View.screens.StartScreen
import com.equipoux.timelyhealth.View.screens.TimelyHealthApp
import com.equipoux.timelyhealth.View.screens.StartScreen


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