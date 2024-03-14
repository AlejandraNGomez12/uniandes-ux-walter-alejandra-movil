package com.equipoUX.timelyhealth.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.equipoUX.timelyhealth.model.models.AddAlarm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

data class AlarmAddUiState(
    val addAlarm: AddAlarm = AddAlarm(),
    val loading: Boolean = false,
    val error: Boolean = false,
    val errorMessage: String? = null,
    val hasSent: Boolean = false
)


class AlarmAddViewModel(
) : ViewModel() {


    private val _uiState = MutableStateFlow(AlarmAddUiState())
    val uiState: StateFlow<AlarmAddUiState> = _uiState.asStateFlow()

    fun addAlbum(success: () -> Unit) {
        _uiState.value = _uiState.value.copy(hasSent = true)
        if (_uiState.value.addAlarm.isValid()) {

            viewModelScope.launch {
                _uiState.value = _uiState.value.copy(loading = true)
                try {
                    success()
                } catch (e: IOException) {
                    _uiState.value = _uiState.value.copy(error = true, errorMessage = e.message)
                } catch (e: HttpException) {
                    _uiState.value = _uiState.value.copy(error = true, errorMessage = e.message)
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString(), e)
                    _uiState.value = _uiState.value.copy(error = true, errorMessage = e.message)
                } finally {
                    _uiState.value = _uiState.value.copy(loading = false)
                }
            }
        }
    }


    fun updateField(addAlarm: AddAlarm) {
        _uiState.value = _uiState.value.copy(addAlarm = addAlarm)
    }

    fun resetFields() {
        _uiState.value = _uiState.value.copy(addAlarm = AddAlarm())
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AlarmAddViewModel()
            }
        }
    }

}
