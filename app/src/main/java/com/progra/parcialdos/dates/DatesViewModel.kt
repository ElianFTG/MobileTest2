package com.progra.parcialdos.dates


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel


class DatesViewModel : ViewModel() {

    var field1 by mutableStateOf("")
        private set

    var field2 by mutableStateOf("")
        private set

    var field3 by mutableStateOf("")
        private set

    var latitude by mutableStateOf(-17.382081453967093)
        private set

    var longitude by mutableStateOf(-66.15540213085374)
        private set

    fun onField1Changed(newText: String) {
        field1 = newText
        // opcional: puedes limpiar los campos 2 y 3 si se escribe texto manual
    }

    fun updateCoordinates(lat: Double, lon: Double) {
        latitude = lat
        longitude = lon
        field2 = lat.toString()
        field3 = lon.toString()
    }

}