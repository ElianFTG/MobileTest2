package com.progra.parcialdos.dates


import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.events.MapEventsReceiver


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
        Spacer(modifier = Modifier.height(10.dp))
        Text("Donde enviaremos tu SIM")
        OutlinedTextField(
            value = viewModel.field1,
            onValueChange = { viewModel.onField1Changed(it) },
            label = { Text("Teléfono de referencia") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.field2,
            onValueChange = {},
            label = { Text("Latitud") },
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = Color.White,
                disabledLabelColor = Color.LightGray,
                disabledBorderColor = Color.DarkGray,
                disabledLeadingIconColor = Color.LightGray,
                disabledTrailingIconColor = Color.LightGray,
                disabledPlaceholderColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = viewModel.field3,
            onValueChange = {},
            label = { Text("Longitud") },
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = Color.White,
                disabledLabelColor = Color.LightGray,
                disabledBorderColor = Color.DarkGray,
                disabledLeadingIconColor = Color.LightGray,
                disabledTrailingIconColor = Color.LightGray,
                disabledPlaceholderColor = Color.LightGray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text("Ubicación estimada", style = MaterialTheme.typography.titleMedium)

        // Aquí mostramos el mapa
        // ✅ Mapa encerrado y limitado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(550.dp)
                .clip(MaterialTheme.shapes.medium)
        ) {
            AndroidView(
                factory = { ctx ->
                    MapView(ctx).apply {
                        setMultiTouchControls(true)
                        controller.setZoom(14.5)
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium),
                update = { mapView ->
                    val point = GeoPoint(viewModel.latitude, viewModel.longitude)
                    mapView.controller.setCenter(point)

                    mapView.overlays.clear()

                    val marker = Marker(mapView).apply {
                        position = point
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        title = "Marcador actual"
                    }
                    mapView.overlays.add(marker)

                    val eventReceiver = object : MapEventsReceiver {
                        override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
                            p?.let {
                                viewModel.updateCoordinates(it.latitude, it.longitude)
                            }
                            return true
                        }

                        override fun longPressHelper(p: GeoPoint?): Boolean = false
                    }

                    val eventsOverlay = MapEventsOverlay(eventReceiver)
                    mapView.overlays.add(eventsOverlay)

                    mapView.invalidate()
                }
            )
        }
    }
}