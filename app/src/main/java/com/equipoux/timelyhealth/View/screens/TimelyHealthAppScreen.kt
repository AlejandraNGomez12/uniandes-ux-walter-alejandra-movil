package com.equipoUX.timelyhealth.view.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.equipoUX.timelyhealth.R
import com.equipoUX.timelyhealth.viewModel.AlarmAddViewModel
import com.equipoUX.timelyhealth.viewModel.AlarmsViewModel
import com.equipoUX.timelyhealth.viewModel.AppoimentAddViewModel
import com.equipoUX.timelyhealth.viewModel.AppoimentsViewModel

enum class TimelyHealthAppScreen(@StringRes val title: Int) {
    Welcome(title = R.string.welcome),
    Start(title=R.string.login_title),
    Alarm(title = R.string.alarms_title_list),
    Appoiment(title = R.string.appoiments_title_list)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimelyHealthAppBar(
    title: Int?,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    logout: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(

        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.back_button),
                    tint = colorResource(id = R.color.blanco)
                )
            }
        },title = { title?.let {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(it),
                    color = colorResource(id = R.color.blanco),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor =  colorResource(id = R.color.azul_3)
        ),
        actions = {

            IconButton(onClick = logout) {
                Icon(
                    painterResource(id = R.drawable.logout),
                    contentDescription = stringResource(R.string.exit_app),
                    tint = colorResource(id = R.color.blanco)
                )
            }

        }
    )
}

@Composable
fun TimelyHealthNavBar(
    navigate: (id: String) -> Unit,
    activeRouteName: String,
    modifier: Modifier = Modifier
) {

        NavigationBar(
            containerColor = (colorResource(id = R.color.azul_3))
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.alarma),
                        contentDescription = stringResource(R.string.go_to_alarm),
                        tint = colorResource(id = R.color.blanco),
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { Text(stringResource(R.string.alarm_title),
                    color = colorResource(id = R.color.blanco)) },
                selected = activeRouteName.startsWith(TimelyHealthAppScreen.Alarm.name),
                onClick = { navigate(TimelyHealthAppScreen.Alarm.name) },
                colors = androidx.compose.material3.NavigationBarItemDefaults
                    .colors(
                        indicatorColor = colorResource(id = R.color.azul_6)
                    )

            )
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.cita),
                        contentDescription = stringResource(R.string.go_to_medical_appointments),
                        tint = colorResource(id = R.color.blanco),
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { Text(stringResource(R.string.medical_appointments_title),
                    color = colorResource(id = R.color.blanco)) },
                selected = activeRouteName.startsWith(TimelyHealthAppScreen.Appoiment.name),
                onClick = { navigate(TimelyHealthAppScreen.Appoiment.name) },
                colors = androidx.compose.material3.NavigationBarItemDefaults
                    .colors(
                        indicatorColor = colorResource(id = R.color.azul_6)
                    )

            )

            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.document),
                        contentDescription = stringResource(R.string.go_to_documents),
                        tint = colorResource(id = R.color.blanco),
                        modifier = Modifier.size(30.dp)
                    )
                },
                label = { Text(stringResource(R.string.documents_title),
                    color = colorResource(id = R.color.blanco)) },
                selected = false,
                onClick = { },
                colors = androidx.compose.material3.NavigationBarItemDefaults
                    .colors(
                        indicatorColor = colorResource(id = R.color.azul_6)
                    )

            )
        }

}

@Composable
fun TimelyHealthApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    alarmsViewModel: AlarmsViewModel= viewModel(factory = AlarmsViewModel.Factory),
    appoimentsViewModel: AppoimentsViewModel= viewModel(factory = AppoimentsViewModel.Factory),
    alarmAddViewModel: AlarmAddViewModel = viewModel(factory = AlarmAddViewModel.Factory),
    appoimentAddViewModel: AppoimentAddViewModel = viewModel(factory = AppoimentAddViewModel.Factory),

    ) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val activeRouteName = backStackEntry?.destination?.route ?: TimelyHealthAppScreen.Start.name
    var screenTitle: Int? = null
    try {
        val selectedScreen = TimelyHealthAppScreen.valueOf(activeRouteName)
        screenTitle = selectedScreen.title
    } catch (_: IllegalArgumentException) {
    }

    Surface(
    ) {
        Scaffold(
            topBar = {
                if (activeRouteName != TimelyHealthAppScreen.Start.name) {
                    TimelyHealthAppBar(
                        title = screenTitle,
                        canNavigateBack = screenTitle == null,
                        navigateUp = { navController.navigateUp() },
                        logout = { navController.navigate(TimelyHealthAppScreen.Start.name) }
                    )
                }
            },
            bottomBar = {
                if (activeRouteName != TimelyHealthAppScreen.Start.name) {
                    TimelyHealthNavBar(
                        navigate = { destination ->
                            navController.navigate(destination)
                        },
                        activeRouteName = activeRouteName,
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = TimelyHealthAppScreen.Welcome.name,
                modifier = modifier.padding(innerPadding)
            )
            {
                composable(route = TimelyHealthAppScreen.Alarm.name) {
                    AlarmsScreen(
                        state = alarmsViewModel.alarmstUiState,
                        retryAction = alarmsViewModel::getAlarms,
                        goToCreate = { navController.navigate("${TimelyHealthAppScreen.Alarm.name}/Add") }
                    )
                }

                composable(
                    route = "${TimelyHealthAppScreen.Alarm.name}/Add"
                ) {
                    alarmAddViewModel.resetFields()
                    AlarmAddScreen(
                        alarmAddViewModel.uiState,
                        addAlbum = alarmAddViewModel::addAlbum,
                        updateField = alarmAddViewModel::updateField,
                        onSuccess = {
                            navController.navigateUp()
                            alarmsViewModel.getAlarms()
                        },
                        navigateUp = { navController.navigateUp() },
                        navController = navController
                    )
                }
                composable(
                    route = "${TimelyHealthAppScreen.Alarm.name}/alerta"
                ) {
                    alarmAddViewModel.resetFields()
                    AlarmAlertaScreen(navController
                    )
                }

                composable(route = TimelyHealthAppScreen.Welcome.name) {
                    WelcomeScreen(
                        navController = navController,
                        modifier = modifier
                    )
                }

                composable(route = TimelyHealthAppScreen.Start.name) {
                    StartScreen(
                        navController = navController,
                        modifier = modifier
                    )
                }
                composable(route = TimelyHealthAppScreen.Appoiment.name) {
                    AppoimentsScreen(
                        appoimentsViewModel.appoimentsUiState,
                        retryAction = appoimentsViewModel::getAppoiments,
                        goToCreate = { navController.navigate("${TimelyHealthAppScreen.Appoiment.name}/Add") }
                        )
                }

                composable(
                    route = "${TimelyHealthAppScreen.Appoiment.name}/Add"
                ) {
                    appoimentAddViewModel.resetFields()
                    AppoimentAddScreen(
                        appoimentAddViewModel.uiState,
                        addAppoiment = appoimentAddViewModel::addAlbum,
                        updateField = appoimentAddViewModel::updateField,
                        onSuccess = {
                            navController.navigateUp()
                            appoimentsViewModel.getAppoiments()
                        },
                        navigateUp = { navController.navigateUp() }
                    )
                }


            }
        }
    }
}