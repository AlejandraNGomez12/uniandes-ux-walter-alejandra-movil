package com.equipoUX.timelyhealth.model.models

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class AddAppoiment(
    val titulo: String?=null,
    val fecha: Date? = null,
    val medicamento: String? = null,
    val archivo: String? = null,
    val especialidad: String? = null,
    val descripcion: String? = null
) {
    fun isValid(): Boolean {
        return !(titulo.isNullOrBlank() ||
                medicamento.isNullOrBlank() ||
                fecha == null ||
                especialidad.isNullOrBlank() ||
                descripcion.isNullOrBlank())
    }
}