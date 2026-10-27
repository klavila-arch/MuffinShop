
package notes.app.ebc.karen.muffinshop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import notes.app.ebc.karen.muffinshop.R
import notes.app.ebc.karen.muffinshop.model.Paso
import notes.app.ebc.karen.muffinshop.model.PedidoUiState

@Composable
fun ResumenScreen(
    state: PedidoUiState,
    onConfirmar: () -> Unit,
    onRegresar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Imagen del resumen
        Image(
            painter = painterResource(
                id = R.drawable.muffin_03
            ),
            contentDescription = "Resumen del pedido de muffins",
            modifier = Modifier.size(140.dp)
        )

        // Título
        Text(text = "Resumen de tu pedido")

        // Información del pedido
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                // Datos del cliente
                Text(text = "Cliente: ${state.nombre}")

                Text(text = "Teléfono: ${state.telefono}")

                Text(text = "Correo: ${state.correo}")

                HorizontalDivider()

                // Características del muffin
                Text(
                    text = "Sabor: ${state.sabor?.name ?: "Sin elegir"}"
                )

                Text(
                    text = "Relleno: ${state.relleno?.nombre ?: "Sin elegir"}"
                )

                Text(text = "Toppings:")

                if (state.toppings.isEmpty()) {
                    Text(text = "Sin toppings")
                } else {
                    state.toppings.forEach { topping ->
                        Text(text = "- ${topping.nombre}")
                    }
                }

                HorizontalDivider()

                // Cantidad y precios
                Text(
                    text = "Cantidad: ${state.cantidad}"
                )

                Text(
                    text = "Precio unitario: $${state.precioUnitario} MXN"
                )

                Text(
                    text = "Total: $${state.total} MXN"
                )
            }
        }

        // Confirmar pedido
        Button(
            onClick = onConfirmar,
            enabled = state.esValido(Paso.RESUMEN)
        ) {
            Text("Confirmar pedido")
        }

        // Regresar
        OutlinedButton(
            onClick = onRegresar
        ) {
            Text("Regresar")
        }
    }
}
