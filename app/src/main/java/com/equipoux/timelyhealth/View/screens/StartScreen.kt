package com.equipoux.timelyhealth.View.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.equipoux.timelyhealth.R


@Composable
fun StartScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val state = rememberScrollState()
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(id = R.color.blanco),
                            colorResource(id = R.color.azul_3)
                        )
                    )
                )
                .alpha(0.4f)
        )

        Image(
            painter = painterResource(id = R.drawable.fondo_pantalla),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.4f),
            contentScale = ContentScale.FillBounds
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
            Row(
                modifier = Modifier.align(Alignment.Start)
                    .padding(start = 56.dp)
            ) {
                Text(
                    text = stringResource(R.string.username),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = TextStyle(
                        color = colorResource(id = R.color.azul_6)
                    ),
                    textAlign = TextAlign.Start
                )
            }
            val username = remember { mutableStateOf(TextFieldValue("")) }
            val password = remember { mutableStateOf(TextFieldValue("")) }

            fun isTextFieldEmpty(textFieldValue: TextFieldValue): Boolean {
                return textFieldValue.text.isEmpty()
            }

            TextField(
                value = username.value,
                onValueChange = { username.value = it },
                textStyle = TextStyle(
                    color = colorResource(id = R.color.azul_6)
                ),
                placeholder  = {
                    Text(
                        text = if (isTextFieldEmpty(username.value)) "Ingrese su usuario" else stringResource(R.string.username_placeholder),
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

            Row(
                modifier = Modifier.align(Alignment.Start)
                    .padding(start = 56.dp)
            ) {
                Text(
                    text = stringResource(R.string.password),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = TextStyle(
                        color = colorResource(id = R.color.azul_6)
                    ),
                    textAlign = TextAlign.Start
                )
            }

            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                textStyle = TextStyle(
                    color = colorResource(id = R.color.azul_6)
                ),
                placeholder  = {
                    Text(
                        text = if (isTextFieldEmpty(password.value)) "Ingrese su contraseña" else stringResource(R.string.username_placeholder),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp),
                        style = TextStyle(
                            color = colorResource(id = R.color.azul_2)
                        ),
                        textAlign = TextAlign.Start
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate("Welcome")
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
                    text = stringResource(R.string.login_title),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
}
