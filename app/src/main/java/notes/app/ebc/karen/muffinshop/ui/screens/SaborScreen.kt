
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
import notes.app.ebc.karen.muffinshop.model.Sabor

@Composable
fun SaborScreen(
    state: PedidoUiState,
    onSeleccionar: (Sabor) -> Unit
) {
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.muffin_02
            ),
            contentDescription = "Sabores disponibles de muffins",
            modifier = Modifier.size(140.dp)
        )

        Text(text = "Elige el sabor de tu muffin")

        Text(text = "Selecciona un sabor para continuar.")

        Sabor.entries.forEach { sabor ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSeleccionar(sabor)
                    }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = sabor.name
                            .lowercase()
                            .replaceFirstChar {
                                it.uppercase()
                            }
                    )

                    Text(
                        text = "$${sabor.precio} MXN por muffin"
                    )

                    if (state.sabor == sabor) {
                        Text(text = "✓ Seleccionado")
                    }
                }
            }
        }
    }
}
