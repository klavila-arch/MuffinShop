
package notes.app.ebc.karen.muffinshop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Datos del cliente")

        OutlinedTextField(
            value = state.nombre,
            onValueChange = onNombre,
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = state.telefono,
            onValueChange = onTelefono,
            label = { Text("Teléfono (10 dígitos)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = state.correo,
            onValueChange = onCorreo,
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )
        )

        Button(
            onClick = onContinuar,
            enabled = state.clienteValido
        ) {
            Text("Continuar")
        }

        OutlinedButton(onClick = onRegresar) {
            Text("Regresar")
        }
    }
}
