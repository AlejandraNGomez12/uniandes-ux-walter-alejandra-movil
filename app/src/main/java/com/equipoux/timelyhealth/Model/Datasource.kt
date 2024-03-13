package com.equipoUX.timelyhealth.model


import com.equipoUX.timelyhealth.model.models.Appoiment

class Datasource {

    companion object {
        val loadAppoimnts: List<Appoiment>
            get() {
                return Datasource().loadAppoimnts()
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