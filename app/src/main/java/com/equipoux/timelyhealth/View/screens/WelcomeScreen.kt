package com.equipoux.timelyhealth.View.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.equipoux.timelyhealth.R

@Composable
fun WelcomeScreen (
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){

    val state = rememberScrollState()
    Box(
    modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.textura),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.4f),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(3f) })
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state)

        ) {
            val shape: Shape = RoundedCornerShape(15.dp)

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(width = 204.dp, height = 96.dp)
                    .clip(shape)
                    .shadow(50.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {

                },
                modifier = Modifier
                    .width(150.dp)
                    .height(70.dp)
                    .padding(bottom = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.azul_4)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.alarm_title),
                    fontSize = 16.sp
                )

            }
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate("${TimelyHealthAppScreen.Appoiment.name}")
                          },
                modifier = Modifier
                    .width(150.dp)
                    .height(70.dp)
                    .padding(bottom = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.azul_4)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.medical_appointments_title),
                    fontSize = 14.sp
                )

            }
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .width(150.dp)
                    .height(70.dp)
                    .padding(bottom = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.azul_4)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.documents_title),
                    fontSize = 16.sp
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {

}