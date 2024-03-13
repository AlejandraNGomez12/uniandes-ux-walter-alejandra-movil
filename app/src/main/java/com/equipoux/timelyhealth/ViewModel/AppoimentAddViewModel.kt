package com.equipoux.timelyhealth.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.equipoux.timelyhealth.Model.models.AddAppoiment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

data class AppoimentAddUiState(
    val addAppoiment: AddAppoiment = AddAppoiment(),
    val loading: Boolean = false,
    val error: Boolean = false,
    val errorMessage: String? = null,
    val hasSent: Boolean = false
)


class AppoimentAddViewModel(
) : ViewModel() {


    private val _uiState = MutableStateFlow(AppoimentAddUiState())
    val uiState: StateFlow<AppoimentAddUiState> = _uiState.asStateFlow()

    fun addAlbum(success: () -> Unit) {
        _uiState.value = _uiState.value.copy(hasSent = true)
        if (_uiState.value.addAppoiment.isValid()) {

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


    fun updateField(addAppoiment: AddAppoiment) {
        _uiState.value = _uiState.value.copy(addAppoiment = addAppoiment)
    }

    fun resetFields() {
        _uiState.value = _uiState.value.copy(addAppoiment = AddAppoiment())
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                AppoimentAddViewModel()
            }
        }
    }

}
