package com.progra.parcialdos.plans

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.progra.parcialdos.navigation.Screen


data class Plan(
    val name: String,
    val oldPrice: String,
    val newPrice: String,
    val gb: String,
    val features: List<String>
)

val plans = listOf(
    Plan(
        name = "Plan FLEX 1",
        oldPrice = "$270",
        newPrice = "$199",
        gb = "5GB",
        features = listOf(
            "Llamadas y SMS ilimitados",
            "Hotspot. Comparte tus datos",
            "Redes Sociales ilimitadas incluidas",
            "Arma tu plan con más apps ilimitadas",
            "CO2 Negativo"
        )
    ),
    Plan(
        name = "Plan FLEX 2",
        oldPrice = "$370",
        newPrice = "$299",
        gb = "8GB",
        features = listOf(
            "Llamadas y SMS limitados",
            "Hotspot. Comparte tus datos",
            "Redes Sociales ilimitadas incluidas",
            "Arma tu plan con más apps ilimitadas",
            "CO2 Negativo"
        )
    ),
    Plan(
        name = "Plan FLEX 3",
        oldPrice = "$470",
        newPrice = "$399",
        gb = "10GB",
        features = listOf(
            "Llamadas y SMS ilimitados",
            "Hotspot. Comparte tus datos",
            "Redes Sociales ilimitadas incluidas",
            "Arma tu plan con más apps ilimitadas",
            "CO2 Negativo"
        )
    )
)

@Composable
fun HomeUI(navController: NavHostController) {
    var currentIndex by remember { mutableStateOf(0) }
    val plan = plans[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text("Nuestros planes móviles", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFFf48b27))
        Text(
            "La mejor cobertura, increíbles beneficios y un compromiso con el planeta.",
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent.copy(0.09f), RoundedCornerShape(16.dp))
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("<", fontSize = 24.sp, modifier = Modifier.clickable {
                    if (currentIndex > 0) currentIndex--
                }.background(color = Color(0xFFf48b27), shape = RoundedCornerShape(55.dp)))


                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(plan.name, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFFf48b27))


                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row {
                            Text("Antes ", color = Color.Gray)
                            Text(plan.oldPrice, textDecoration = TextDecoration.LineThrough,color = Color(0xFFEA4E4E))
                            Text(" /mes", color = Color.Gray)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Row {
                            Text("Antes ", color = Color.Gray)
                            Text(plan.newPrice, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                            Text(" /mes", color = Color.Gray)
                        }

                    }
                    Text(plan.gb, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                        plan.features.forEach {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text("✔ $it", fontSize = 14.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { navController.navigate(Screen.DatesScreen.route) } , colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFf48b27),
                        contentColor = colorScheme.onPrimary
                    )) {
                        Text("Quiero este plan", color = colorScheme.onPrimary)
                    }

                }

                Text(">", fontSize = 24.sp, modifier = Modifier.clickable {
                    if (currentIndex < plans.size - 1) currentIndex++
                }.background(color = Color(0xFFf48b27), shape = RoundedCornerShape(55.dp)))

            }
        }
    }
}