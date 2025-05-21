package com.progra.parcialdos.plans

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.compose.runtime.Composable
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

data class Plan(
    val titulo: String,
    val precio: String,
    val datos: String,
    val beneficios: List<String>
)
@Composable
fun PlansUI() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewModel : CounterViewModel = viewModel()
    var cadena by remember { mutableStateOf<String>("0") }

    fun updateUI(s: String) {
        cadena = s
    }
    viewModel.cadena.observe(
        lifecycleOwner,
        Observer(::updateUI)
    )
    val context = LocalContext.current
    Scaffold {
            innerPadding ->  Column(
        modifier = Modifier.padding(innerPadding).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(cadena)
        Button(
            onClick = {
                viewModel.increment( context =  context)
            }
        ) {
            Text("Increment")
        }
    }
    }


}