
package notes.app.ebc.karen.muffinshop.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import notes.app.ebc.karen.muffinshop.R
import notes.app.ebc.karen.muffinshop.model.PedidoUiState

@Composable
fun ClientesScreen(
    state: PedidoUiState,
    onNombre: (String) -> Unit,
    onTelefono: (String) -> Unit,
    onCorreo: (String) -> Unit,
    onContinuar: () -> Unit,
    onRegresar: () -> Unit
) {
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // Imagen de datos del cliente
        Image(
            painter = painterResource(
                id = R.drawable.muffin_06
            ),
            contentDescription = "Formulario de datos del cliente",
            modifier = Modifier.size(140.dp)
        )

        // Título
        Text(text = "Datos del cliente")

        // Nombre completo
        OutlinedTextField(
            value = state.nombre,
            onValueChange = onNombre,
            label = {
                Text("Nombre completo")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        // Teléfono
        OutlinedTextField(
            value = state.telefono,
            onValueChange = onTelefono,
            label = {
                Text("Teléfono (10 dígitos)")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            )
        )

        // Correo electrónico
        OutlinedTextField(
            value = state.correo,
            onValueChange = onCorreo,
            label = {
                Text("Correo electrónico")
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )
        )

        // Botón continuar
        Button(
            onClick = onContinuar,
            enabled = state.clienteValido
        ) {
            Text("Continuar")
        }

        // Botón regresar
        OutlinedButton(
            onClick = onRegresar
        ) {
            Text("Regresar")
        }
    }
}
