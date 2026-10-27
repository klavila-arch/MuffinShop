
package notes.app.ebc.karen.muffinshop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import notes.app.ebc.karen.muffinshop.R
import notes.app.ebc.karen.muffinshop.model.PedidoUiState
import notes.app.ebc.karen.muffinshop.model.Topping

@Composable
fun ToppingScreen(
    state: PedidoUiState,
    onAlternar: (Topping) -> Unit
) {
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.muffin_01
            ),
            contentDescription = "Opciones de toppings para muffins",
            modifier = Modifier.size(140.dp)
        )

        Text(text = "Elige los toppings de tu muffin")

        Text(
            text = "Puedes seleccionar varios toppings o elegir sin toppings."
        )

        Topping.entries.forEach { topping ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onAlternar(topping)
                    }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = topping.name
                            .lowercase()
                            .replace("_", " ")
                    )

                    Text(
                        text = if (topping.extra == 0) {
                            "Sin costo adicional"
                        } else {
                            "+$${topping.extra} MXN"
                        }
                    )

                    if (topping in state.toppings) {
                        Text(text = "✓ Seleccionado")
                    }
                }
            }
        }
    }
}
