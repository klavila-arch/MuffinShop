
package notes.app.ebc.karen.muffinshop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import notes.app.ebc.karen.muffinshop.model.PedidoUiState

@Composable
fun CantidadScreen(
    state: PedidoUiState,
    onCambiarCantidad: (Int) -> Unit,
    onContinuar: () -> Unit,
    onRegresar: () -> Unit
) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Elige la cantidad de muffins")
        Text(text = "Puedes pedir entre 1 y 12 muffins.")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = {
                    onCambiarCantidad(state.cantidad - 1)
                },
                enabled = state.cantidad > 1
            ) {
                Text("-")
            }

            Text(text = "${state.cantidad}")

            OutlinedButton(
                onClick = {
                    onCambiarCantidad(state.cantidad + 1)
                },
                enabled = state.cantidad < 12
            ) {
                Text("+")
            }
        }

        Text(text = "Precio unitario: $${state.precioUnitario} MXN")
        Text(text = "Subtotal: $${state.total} MXN")

        Button(
            onClick = onContinuar,
            enabled = state.esValido(
                notes.app.ebc.karen.muffinshop.model.Paso.CANTIDAD
            )
        ) {
            Text("Continuar")
        }

        OutlinedButton(onClick = onRegresar) {
            Text("Regresar")
        }
    }
}
