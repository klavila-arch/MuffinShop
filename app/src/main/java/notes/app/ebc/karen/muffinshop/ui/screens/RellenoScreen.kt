
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
import notes.app.ebc.karen.muffinshop.model.Relleno

@Composable
fun RellenoScreen(
    state: PedidoUiState,
    onSeleccionar: (Relleno) -> Unit
) {
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.muffin_04
            ),
            contentDescription = "Opciones de relleno para muffins",
            modifier = Modifier.size(140.dp)
        )

        Text(text = "Elige el relleno de tu muffin")

        Text(
            text = "Selecciona un relleno. También puedes elegir sin relleno."
        )

        Relleno.entries.forEach { relleno ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSeleccionar(relleno)
                    }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(text = relleno.nombre)

                    Text(
                        text = if (relleno.extra == 0) {
                            "Sin costo adicional"
                        } else {
                            "+$${relleno.extra} MXN"
                        }
                    )

                    if (state.relleno == relleno) {
                        Text(text = "✓ Seleccionado")
                    }
                }
            }
        }
    }
}
