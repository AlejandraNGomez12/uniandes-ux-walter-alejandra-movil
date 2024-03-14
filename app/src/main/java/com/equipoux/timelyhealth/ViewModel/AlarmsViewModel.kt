package com.equipoUX.timelyhealth.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.equipoUX.timelyhealth.TimelyHealthApplication
import com.equipoUX.timelyhealth.model.Datasource
import com.equipoUX.timelyhealth.model.models.Alarm
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface AlarmsUiState {
    data class Success(val alarms: List<Alarm>) : AlarmsUiState
    object Error: AlarmsUiState
    object Loading : AlarmsUiState
}
class AlarmsViewModel(): ViewModel() {

    var alarmstUiState: AlarmsUiState by mutableStateOf(AlarmsUiState.Loading)
        private set

    init{
        getAlarms()
    }

    fun getAlarms(){
        viewModelScope.launch {
            alarmstUiState = AlarmsUiState.Loading
            alarmstUiState = try {
                AlarmsUiState.Success(Datasource.loadAlarms)
            } catch (e: IOException){
                AlarmsUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AlarmsViewModel()
            }
        }
    }

}

