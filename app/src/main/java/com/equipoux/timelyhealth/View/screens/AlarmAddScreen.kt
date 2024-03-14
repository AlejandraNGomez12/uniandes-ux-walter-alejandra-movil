package com.equipoUX.timelyhealth.view.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.equipoUX.timelyhealth.R
import com.equipoUX.timelyhealth.model.models.AddAlarm
import com.equipoUX.timelyhealth.viewModel.AlarmAddUiState
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun AlarmAddScreen(
    state: StateFlow<AlarmAddUiState>,
    addAlbum: (success: () -> Unit) -> Unit,
    updateField: (addAlarm: AddAlarm) -> Unit,
    navigateUp: () -> Unit,
    onSuccess: () -> Unit,
    navController: NavHostController

    ) {

    val alarmAddUiState by state.collectAsState()
    AlarmAdd(
        state = alarmAddUiState,
        addAlarm = addAlbum,
        updateField = updateField,
        navigateUp = navigateUp,
        onSuccess = onSuccess,
        navController = navController

    )
}

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmAdd(
    state: AlarmAddUiState,
    addAlarm: (() -> Unit) -> Unit,
    updateField: (addAlarm: AddAlarm) -> Unit,
    navigateUp: () -> Unit,
    onSuccess: () -> Unit,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val recordMedicine = stringArrayResource(id = R.array.record_medicine)
    val recordTone = stringArrayResource(id = R.array.record_tone)

    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(recordMedicine[0]) }

    val contextTone = LocalContext.current
    var expandedTone by remember { mutableStateOf(false) }
    var selectedTextTone by remember { mutableStateOf(recordMedicine[0]) }

    Surface(color = colorResource(id = R.color.blanco)) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.add_alarm),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = colorResource(id = R.color.azul_6),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Text(
                text = stringResource(R.string.title),
                color = colorResource(id = R.color.azul_6),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )


            OutlinedTextField(
                value = state.addAlarm.titulo ?: "",
                onValueChange = {
                    updateField(state.addAlarm.copy(titulo = it))
                },
                singleLine = true,
                label = {
                    Text(
                        text = stringResource(R.string.title),
                        color = colorResource(id = R.color.azul_6)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                isError = state.addAlarm.titulo.isNullOrBlank() && state.hasSent,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.azul_6),
                    unfocusedBorderColor = colorResource(id = R.color.azul_6)
                )

            )

            if (state.addAlarm.titulo.isNullOrBlank() && state.hasSent) {
                ErrorMessage("El título es obligatorio.")
            }

            Text(
                text = stringResource(R.string.medicine),
                color = colorResource(id = R.color.azul_6),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    .border(1.dp, colorResource(id = R.color.azul_6), RoundedCornerShape(8.dp))

            ) {
                Text(
                    text = stringResource(R.string.medicine),
                    color = colorResource(id = R.color.azul_6),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = selectedText,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = colorResource(id = R.color.blanco)
                        ),
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        recordMedicine.forEach { item ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = item,
                                        color = colorResource(id = R.color.azul_6)
                                    )
                                },
                                onClick = {
                                    selectedText = item
                                    updateField(state.addAlarm.copy(medicamento = item))
                                    expanded = false
                                    Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }
                }
            }

            if (state.addAlarm.medicamento.isNullOrBlank() && state.hasSent) {
                ErrorMessage("Seleccione el medicamento")
            }


            Text(
                text = stringResource(R.string.tone),
                color = colorResource(id = R.color.azul_6),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                    .border(1.dp, colorResource(id = R.color.azul_6), RoundedCornerShape(8.dp))

            ) {
                ExposedDropdownMenuBox(
                    expanded = expandedTone,
                    onExpandedChange = {
                        expandedTone = !expandedTone
                    }
                ) {
                    TextField(
                        value = selectedTextTone,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTone) },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = colorResource(id = R.color.blanco)
                        ),
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedTone,
                        onDismissRequest = { expandedTone = false }
                    ) {
                        recordTone.forEach { itemTono ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = itemTono,
                                        color = colorResource(id = R.color.azul_6)
                                    )
                                },
                                onClick = {
                                    selectedTextTone = itemTono
                                    updateField(state.addAlarm.copy(tono = itemTono))
                                    expandedTone = false
                                    Toast.makeText(contextTone, itemTono, Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }
                }
            }

            if (state.addAlarm.tono.isNullOrBlank() && state.hasSent) {
                ErrorMessage("Seleccione el tono")
            }


            Text(
                text = stringResource(R.string.date),
                color = colorResource(id = R.color.azul_6),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            CustomDatePicker(state.hasSent, onValueChange = {
                updateField(state.addAlarm.copy(fecha = it))
            })

            if (state.addAlarm.fecha == null && state.hasSent) {
                ErrorMessage("Seleccione la fecha ")
            }

            Text(
                text = stringResource(R.string.description),
                color = colorResource(id = R.color.azul_6),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = state.addAlarm.descripcion ?: "",
                onValueChange = {
                    updateField(state.addAlarm.copy(descripcion = it))
                },
                label = {
                    Text(
                        stringResource(R.string.description),
                        color = colorResource(id = R.color.azul_6)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                isError = state.addAlarm.descripcion.isNullOrBlank() && state.hasSent,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.azul_6),
                    unfocusedBorderColor = colorResource(id = R.color.azul_6)
                )
            )

            if (state.addAlarm.descripcion.isNullOrBlank() && state.hasSent) {
                ErrorMessage("La descripción es obligatoria")
            }

            if (!state.loading) {
                val dialogShown = remember { mutableStateOf(false) }

                if (dialogShown.value) {
                    AlertDialog(
                        onDismissRequest = {
                            dialogShown.value = false
                            onSuccess()
                        },
                        title = {
                            Text(
                                "Guardado exitosamente",
                                color = colorResource(id = R.color.azul_6)
                            )

                        },
                        containerColor = colorResource(id = R.color.azul_1),
                        confirmButton = {
                            Button(
                                onClick = {
                                    onSuccess()
                                    navController.navigate("${TimelyHealthAppScreen.Alarm.name}/alerta")
                                    dialogShown.value = false

                                },
                                colors = ButtonDefaults.buttonColors(
                                    colorResource(id = R.color.azul_4)
                                )
                            ) {
                                Text("Salir", color = colorResource(id = R.color.blanco))
                            }
                        }
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = navigateUp,
                        colors = ButtonDefaults.buttonColors(
                            colorResource(id = R.color.azul_4)
                        )
                    ) {
                        Text(
                            stringResource(R.string.cancel),
                            color = colorResource(id = R.color.blanco)
                        )
                    }

                    Button(
                        onClick = {
                            addAlarm {
                                dialogShown.value = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            colorResource(id = R.color.azul_4)
                        )
                    ) {
                        Text("Guardar", color = colorResource(id = R.color.blanco))
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun AlarmAddPreview() {
    CustomDatePicker(false, onValueChange = {})
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(hasSent: Boolean, onValueChange: (Date) -> Unit) {

    val datePickerState = rememberDatePickerState()

    val formatter = SimpleDateFormat("MMM dd yyyy", Locale("es", "co"))
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    var selectedDate: Long? by remember {
        mutableStateOf(null)
    }
    val offsetHours = (1000L * 60 * 60 * 5)
    if (showDatePicker) {

        val scrollState = rememberScrollState()

        DatePickerDialog(
            modifier = Modifier.verticalScroll(scrollState),
            colors = DatePickerDefaults.colors(
                containerColor = Color.LightGray, //calendar background color
            ),
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    onValueChange(Date(datePickerState.selectedDateMillis!! + offsetHours))
                    selectedDate = datePickerState.selectedDateMillis!! + offsetHours

                },
                    colors = ButtonDefaults.textButtonColors(contentColor = colorResource(id = R.color.azul_6)) // Cambia el color del texto del botón de confirmación
                ) {
                    Text(
                        text = stringResource(R.string.add),
                        color = colorResource(id = R.color.azul_6)
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker = false
                },
                    colors = ButtonDefaults.textButtonColors(contentColor = colorResource(id = R.color.azul_6)) // Cambia el color del texto del botón de cancelación
                ) {
                    Text(text = stringResource(R.string.cancel),
                        color = colorResource(id = R.color.azul_6))
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    titleContentColor = colorResource(id = R.color.azul_6),
                    selectedDayContainerColor = colorResource(id = R.color.azul_2),
                    todayContentColor = colorResource(id = R.color.blanco),
                    todayDateBorderColor = colorResource(id = R.color.azul_2),
                    dayContentColor =colorResource(id = R.color.azul_6),
                ),
                title = {
                    Text(
                        text = "Seleccione la fecha",
                        modifier = Modifier.padding(top = 12.dp, start = 24.dp),
                        color = colorResource(id = R.color.azul_6)
                    )
                },
                headline = {
                    Text(
                        text = datePickerState.selectedDateMillis?.let { formatter.format(Date(it + offsetHours)) }
                            ?: "",
                        modifier = Modifier.padding(bottom = 12.dp, start = 24.dp),
                        color = colorResource(id = R.color.azul_6)
                    )
                }
            )
        }
    }

    OutlinedTextField(
        value = selectedDate?.let { formatter.format(Date(it)) } ?: "",
        onValueChange = {},
        label = { Text("Fecha",
            color = colorResource(id = R.color.azul_6)) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next
        ),
        readOnly = true,
        trailingIcon = {
            IconButton(
                onClick = { showDatePicker = true }
            ) {
                Icon(Icons.Filled.DateRange, contentDescription = "Calendario",tint = colorResource(id = R.color.azul_6) )
            }
        },
        isError = (selectedDate == null) && hasSent,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .background(Color.White),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.azul_6),
            unfocusedBorderColor = colorResource(id = R.color.azul_6)
        )
    )


}


@Composable
fun ErrorMessage(message: String) {
    Text(
        message,
        style = MaterialTheme.typography.labelLarge,
        color = (colorResource(id = R.color.rojo_4)),
        textAlign = TextAlign.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    )
}


