
package notes.app.ebc.karen.muffinshop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import notes.app.ebc.karen.muffinshop.model.Paso
import notes.app.ebc.karen.muffinshop.model.PedidoUiState

@Composable
fun ResumenScreen(
    state: PedidoUiState,
    onConfirmar: () -> Unit,
    onRegresar: () -> Unit
) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Resumen de tu pedido")

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("Cliente: ${state.nombre}")
                Text("Teléfono: ${state.telefono}")
                Text("Correo: ${state.correo}")

                HorizontalDivider()

                Text("Sabor: ${state.sabor?.name ?: "Sin elegir"}")
                Text(
                    "Relleno: ${state.relleno?.nombre ?: "Sin elegir"}"
                )

                Text("Toppings:")
                state.toppings.forEach { topping ->
                    Text("- ${topping.nombre}")
                }

                HorizontalDivider()

                Text("Cantidad: ${state.cantidad}")
                Text("Precio unitario: $${state.precioUnitario} MXN")
                Text("Total: $${state.total} MXN")
            }
        }

        Button(
            onClick = onConfirmar,
            enabled = state.esValido(Paso.RESUMEN)
        ) {
            Text("Confirmar pedido")
        }

        OutlinedButton(onClick = onRegresar) {
            Text("Regresar")
        }
    }
}
