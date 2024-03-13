package com.equipoux.timelyhealth.Model.models

import java.util.Date

data class Alarm(
    val id: Long,
    val titulo: String,
    val medicamento: String? = null,
    val fecha: Date? = null,
    val tono: String? = null,
    val descripcion: String? = null
)