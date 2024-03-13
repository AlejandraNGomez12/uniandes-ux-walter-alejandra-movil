package com.equipoux.timelyhealth.View.screens

import ErrorScreen
import LoadingScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.equipoux.timelyhealth.R
import com.equipoux.timelyhealth.model.models.Appoiment
import com.equipoux.timelyhealth.ViewModel.AppoimentsUiState

@Composable
fun AppoimentsScreen(state: AppoimentsUiState, retryAction: () -> Unit,
                     goToCreate: () -> Unit) {

    when (state) {
        is AppoimentsUiState.Loading -> LoadingScreen()
        is AppoimentsUiState.Success -> AppoimentsList(
            appoimentsList = state.appoiments,
            goToCreate = goToCreate
        )

        is AppoimentsUiState.Error -> ErrorScreen(retryAction)
    }

}

@Composable
fun AppoimentsList(
    appoimentsList: List<Appoiment>,
    goToCreate: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(color = colorResource(id = R.color.blanco)) {

        Column(
            horizontalAlignment = Alignment.End,
            modifier = modifier
                .fillMaxSize()

        ) {
            Spacer(modifier = modifier.height(16.dp))
            Button(
                onClick = goToCreate,
                modifier = modifier
                    .width(150.dp)
                    .height(70.dp)
                    .padding(bottom = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.azul_4)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row {
                    Icon(Icons.Default.AddCircle, contentDescription = "Add Icon")

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.add),
                        fontSize = 16.sp,
                        color = (colorResource(id = R.color.blanco)),
                    )
                }

            }

            LazyColumn() {

                items(appoimentsList) { appoiment ->
                    AppoimentCard(appoiment = appoiment)
                    Divider()

                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppoimentCard(appoiment: Appoiment,  modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(10.dp))
    ListItem(
        modifier = Modifier
            .background(color = colorResource(id = R.color.azul_3))
            .padding(horizontal = 12.dp, vertical = 2.dp),
        headlineContent = {
            Text(
                appoiment.titulo,
                color =(colorResource(id = R.color.blanco))
            ) },
        trailingContent = {
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "Ir a la alarma ${appoiment.titulo}",
                    tint = (colorResource(id = R.color.blanco)),
                    modifier = Modifier.padding(8.dp)
                )
            }
        },
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.blanco)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = appoiment.titulo.first().uppercase(),
                    color =(colorResource(id = R.color.azul_6)),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        colors = ListItemDefaults.colors(
            containerColor = colorResource(id = R.color.azul_3)
        )
    )
}