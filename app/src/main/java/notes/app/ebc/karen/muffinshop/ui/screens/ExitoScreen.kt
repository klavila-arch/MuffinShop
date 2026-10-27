
package notes.app.ebc.karen.muffinshop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import notes.app.ebc.karen.muffinshop.R
import notes.app.ebc.karen.muffinshop.model.PedidoUiState

@Composable
fun ExitoScreen(
    state: PedidoUiState,
    onNuevoPedido: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            Alignment.CenterVertically
        )
    ) {

        // Imagen de confirmación
        Image(
            painter = painterResource(
                id = R.drawable.muffin_05
            ),
            contentDescription = "Pedido confirmado correctamente",
            modifier = Modifier.size(180.dp)
        )

        // Mensaje principal
        Text(
            text = "¡Pedido confirmado!",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        // Agradecimiento personalizado
        Text(
            text = "Gracias por tu compra, ${state.nombre}.",
            textAlign = TextAlign.Center
        )

        // Cantidad
        Text(
            text = "Cantidad de muffins: ${state.cantidad}",
            textAlign = TextAlign.Center
        )

        // Total
        Text(
            text = "Total del pedido: $${state.total} MXN",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        // Mensaje informativo
        Text(
            text = "Tu pedido se registró en esta sesión de la aplicación.",
            textAlign = TextAlign.Center
        )

        // Nuevo pedido
        Button(
            onClick = onNuevoPedido
        ) {
            Text("Hacer otro pedido")
        }
    }
}
