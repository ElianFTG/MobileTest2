package com.progra.parcialdos.dates


import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


@SuppressLint("UnsafeOptInUsageError")
@Composable
fun DatesUI(viewModel: DatesViewModel = viewModel()) {

    val context = LocalContext.current

    // Necesario para inicializar osmdroid
    LaunchedEffect(Unit) {
        Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE))
    }


    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
    ) {

        OutlinedTextField(
            value = viewModel.field1,
            onValueChange = { viewModel.onField1Changed(it) },
            label = { Text("Campo 1 (editable)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.field2,
            onValueChange = {},
            label = { Text("Campo 2 (autocompletado)") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.field3,
            onValueChange = {},
            label = { Text("Campo 3 (autocompletado)") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Ubicación estimada", style = MaterialTheme.typography.titleMedium)

        // Aquí mostramos el mapa
        AndroidView(
            factory = {
                MapView(it).apply {
                    setMultiTouchControls(true)
                    controller.setZoom(14.5)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            update = { mapView ->
                val geoPoint = GeoPoint(viewModel.latitude, viewModel.longitude)
                mapView.controller.setCenter(geoPoint)

                mapView.overlays.clear() // limpia marcadores anteriores
                val marker = Marker(mapView)
                marker.position = geoPoint
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                marker.title = "Ubicación"
                mapView.overlays.add(marker)
                mapView.invalidate()
            }
        )
    }
}