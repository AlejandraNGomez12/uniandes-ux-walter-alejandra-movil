package com.equipoUX.timelyhealth.view.screens

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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.equipoUX.timelyhealth.R
import com.equipoUX.timelyhealth.model.models.AddAppoiment
import com.equipoUX.timelyhealth.viewModel.AppoimentAddUiState
import kotlinx.coroutines.flow.StateFlow


@Composable
fun AppoimentAddScreen(
    state: StateFlow<AppoimentAddUiState>,
    addAppoiment: (success: () -> Unit) -> Unit,
    updateField: (addAppoiment: AddAppoiment) -> Unit,
    navigateUp: () -> Unit,
    onSuccess: () -> Unit
) {

    val appoimentAddUiState by state.collectAsState()
    AppoimentAdd(
        state = appoimentAddUiState,
        addAppoiment = addAppoiment,
        updateField = updateField,
        navigateUp = navigateUp,
        onSuccess = onSuccess
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppoimentAdd(
    state: AppoimentAddUiState,
    addAppoiment: (() -> Unit) -> Unit,
    updateField: (addAppoiment: AddAppoiment) -> Unit,
    navigateUp: () -> Unit,
    onSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val recordMedicine = stringArrayResource(id = R.array.record_medicine)
    val recordTone = stringArrayResource(id = R.array.record_tone)
    var selectedFile by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(recordMedicine[0]) }

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
                        text = stringResource(R.string.add_appoiment),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
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
                value = state.addAppoiment.titulo ?: "",
                onValueChange = {
                    updateField(state.addAppoiment.copy(titulo = it))
                },
                singleLine = true,
                label = { Text(text = stringResource(R.string.title)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                isError = state.addAppoiment.titulo.isNullOrBlank() && state.hasSent,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
            )

            if (state.addAppoiment.titulo.isNullOrBlank() && state.hasSent) {
                ErrorMessage("El título es obligatorio.")
            }

            Text(
                text = stringResource(R.string.date),
                color = colorResource(id = R.color.azul_6),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            CustomDatePicker(state.hasSent, onValueChange = {
                updateField(state.addAppoiment.copy(fecha = it))
            })

            if (state.addAppoiment.fecha == null && state.hasSent) {
                ErrorMessage("Seleccione la fecha ")
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
                                    updateField(state.addAppoiment.copy(medicamento = item))
                                    expanded = false
                                    Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }
                }
            }

            if (state.addAppoiment.medicamento.isNullOrBlank() && state.hasSent) {
                ErrorMessage("Seleccione el medicamento")
            }

            Text(
                text = stringResource(R.string.file),
                color = colorResource(id = R.color.azul_6),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedButton(
                onClick = {
                    selectedFile = "Archivo seleccionado"
                },
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(stringResource(R.string.file))
            }

            selectedFile?.let { fileName ->
                Text(
                    text = "Archivo seleccionado: $fileName",
                    color = colorResource(id = R.color.azul_6),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Text(
                text = stringResource(R.string.specialty),
                color = colorResource(id = R.color.azul_6),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = state.addAppoiment.especialidad ?: "",
                onValueChange = {
                    updateField(state.addAppoiment.copy(especialidad = it))
                },
                label = { Text(stringResource(R.string.specialty)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                isError = state.addAppoiment.especialidad.isNullOrBlank() && state.hasSent,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth(),
            )

            if (state.addAppoiment.especialidad.isNullOrBlank() && state.hasSent) {
                ErrorMessage("La especialidad es obligatoria")
            }

            Text(
                text = stringResource(R.string.description),
                color = colorResource(id = R.color.azul_6),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = state.addAppoiment.descripcion ?: "",
                onValueChange = {
                    updateField(state.addAppoiment.copy(descripcion = it))
                },
                label = { Text(stringResource(R.string.description)) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                isError = state.addAppoiment.descripcion.isNullOrBlank() && state.hasSent,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth(),
            )

            if (state.addAppoiment.descripcion.isNullOrBlank() && state.hasSent) {
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
                            addAppoiment {
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
