package com.equipoux.timelyhealth.model


import com.equipoux.timelyhealth.Model.models.Appoiment
import com.equipoux.timelyhealth.Model.models.Alarm

class Datasource {

    fun loadAlarms() : List<Alarm> {
        return listOf(
            Alarm(id = 1, titulo = "Medicina General"),
            Alarm(id = 2, titulo = "Control Diario"),
            Alarm(id = 3, titulo = "Rayo X"),
            Alarm(id = 4, titulo = "Fisioterapeuta")
        )
    }

    companion object {
        val loadAppoimnts: List<Appoiment>
            get() {
                return Datasource().loadAppoimnts()
            }
        val loadAlarms: List<Alarm>
            get() {
                return Datasource().loadAlarms()
            }
    }


    fun loadAppoimnts() : List<Appoiment> {
        return listOf(
            Appoiment(id = 1, titulo = "Losartan"),
            Appoiment(id = 2, titulo = "Ibuprofeno"),
            Appoiment(id = 3, titulo = "Aspirina"),
            Appoiment(id = 4, titulo = "XRayDol"),
            Appoiment(id = 5, titulo = "Buscapina"),
            Appoiment(id = 6, titulo = "Noraver"),
            Appoiment(id = 7, titulo = "Salbutamol")
        )
    }

}