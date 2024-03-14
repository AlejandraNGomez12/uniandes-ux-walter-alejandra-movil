package com.equipoUX.timelyhealth.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.equipoUX.timelyhealth.R


@Composable
fun AlarmAlertaScreen(    navController: NavHostController
) {

    AlarmAlerta(     navController= navController
    )
}

@Composable
fun AlarmAlerta(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val username = remember { mutableStateOf(TextFieldValue("")) }
    val observacion = remember { mutableStateOf(TextFieldValue("")) }
    val dialogShown = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = colorResource(id = R.color.azul_1)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier.padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Alarma Sonando", color = colorResource(id = R.color.azul_6))
        }

        Row(
            modifier = Modifier.padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Alarma: Medicamento 1", color = colorResource(id = R.color.azul_6))
        }

        Row(
            modifier = Modifier
                .padding(start = 56.dp)
                .align(Alignment.Start)

        ) {
            Text(
                text = stringResource(R.string.observacion),
                fontSize = 14.sp,
                style = TextStyle(
                    color = colorResource(id = R.color.azul_6)
                ),
            )
        }

        TextField(
            value = observacion.value,
            onValueChange = { observacion.value = it },
            textStyle = TextStyle(
                color = colorResource(id = R.color.azul_6)
            ),
            placeholder = {
                Text(
                    text = stringResource(
                        R.string.description),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = TextStyle(
                        color = colorResource(id = R.color.azul_2)
                    ),
                    textAlign = TextAlign.Start
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(12.dp))

        if (dialogShown.value) {
            AlertDialog(
                onDismissRequest = {
                    dialogShown.value = false
                },
                title = {
                    Text("La notificación y observaciones se enviaron a los contactos relacionados ", color = colorResource(id = R.color.azul_6))
                },
                containerColor = colorResource(id = R.color.azul_1),
                confirmButton = {
                    Button(
                        onClick = {
                            navController.navigate("${TimelyHealthAppScreen.Alarm.name}")
                            dialogShown.value = false

                        },
                        colors = ButtonDefaults.buttonColors(
                            colorResource(id = R.color.azul_4)
                        ),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text("Salir", color = colorResource(id = R.color.blanco))
                    }
                }
            )
        }

        Button(
            onClick = {
                dialogShown.value = true
            },
            modifier = Modifier
                .width(150.dp)
                .height(70.dp)
                .padding(bottom = 24.dp),

            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.azul_6)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = stringResource(R.string.enviar),
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
private fun AlarmAddPreview() {
    AlarmAlerta()
}
