package com.equipoUX.timelyhealth.model.models

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class AddAlarm(
    val titulo: String?=null,
    val medicamento: String? = null,
    val fecha: Date? = null,
    val tono: String? = null,
    val descripcion: String? = null
) {
    fun isValid(): Boolean {
        return !(titulo.isNullOrBlank() ||
                medicamento.isNullOrBlank() ||
                fecha == null ||
                tono.isNullOrBlank() ||
                descripcion.isNullOrBlank())
    }
}