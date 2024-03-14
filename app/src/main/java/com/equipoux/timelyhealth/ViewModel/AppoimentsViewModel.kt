package com.equipoUX.timelyhealth.viewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.equipoUX.timelyhealth.model.Datasource
import com.equipoUX.timelyhealth.model.models.Appoiment
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface AppoimentsUiState {
    data class Success(val appoiments: List<Appoiment>) : AppoimentsUiState
    object Error: AppoimentsUiState
    object Loading : AppoimentsUiState
}
class AppoimentsViewModel(): ViewModel() {

    var appoimentsUiState: AppoimentsUiState by mutableStateOf(AppoimentsUiState.Loading)
        private set

    init{
        getAppoiments()
    }

    fun getAppoiments(){
        viewModelScope.launch {
            appoimentsUiState = AppoimentsUiState.Loading
            appoimentsUiState = try {
                AppoimentsUiState.Success(Datasource.loadAppoimnts)
            } catch (e: IOException){
                AppoimentsUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AppoimentsViewModel()
            }
        }
    }

}

