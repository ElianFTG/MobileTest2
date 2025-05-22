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

    // Coordenadas (opcional)
    var latitude by mutableStateOf(40.4168)  // Ej: Madrid
    var longitude by mutableStateOf(-3.7038)

    fun onField1Changed(newText: String) {
        field1 = newText
        field2 = "Auto: ${newText.uppercase()}"
        field3 = "Sugerencia: ${newText.reversed()}"

        // Simular cambio de ubicación según entrada (opcional)
        if (newText.lowercase() == "paris") {
            latitude = 48.8566
            longitude = 2.3522
        }
    }

}