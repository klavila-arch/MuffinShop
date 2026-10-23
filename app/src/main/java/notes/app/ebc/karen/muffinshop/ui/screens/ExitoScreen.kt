
package notes.app.ebc.karen.muffinshop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import notes.app.ebc.karen.muffinshop.model.PedidoUiState

@Composable
fun ExitoScreen(
    state: PedidoUiState,
    onNuevoPedido: () -> Unit
) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("¡Pedido confirmado!")

        Text("Gracias por tu compra, ${state.nombre}.")

        Text("Cantidad de muffins: ${state.cantidad}")

        Text("Total del pedido: $${state.total} MXN")

        Text("Tu pedido se registró en esta sesión de la aplicación.")

        Button(onClick = onNuevoPedido) {
            Text("Hacer otro pedido")
        }
    }
}
