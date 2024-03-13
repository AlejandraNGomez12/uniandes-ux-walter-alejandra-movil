package com.equipoux.timelyhealth.Model.models

import java.util.Date


data class Appoiment(
    val id:Long,
    val titulo:String,
    val medicamento:String? = null,
    val archivo:String? = null,
    val fecha: Date? = null,
    val especialidad:String? = null,
    val descricion:String? = null,
)