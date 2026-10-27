
package notes.app.ebc.karen.muffinshop.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import notes.app.ebc.karen.muffinshop.R
import notes.app.ebc.karen.muffinshop.model.Paso
import notes.app.ebc.karen.muffinshop.model.PedidoUiState
import notes.app.ebc.karen.muffinshop.ui.screens.CantidadScreen
import notes.app.ebc.karen.muffinshop.ui.screens.ClientesScreen
import notes.app.ebc.karen.muffinshop.ui.screens.ExitoScreen
import notes.app.ebc.karen.muffinshop.ui.screens.RellenoScreen
import notes.app.ebc.karen.muffinshop.ui.screens.ResumenScreen
import notes.app.ebc.karen.muffinshop.ui.screens.SaborScreen
import notes.app.ebc.karen.muffinshop.ui.screens.ToppingScreen
import notes.app.ebc.karen.muffinshop.viewmodel.PedidoViewModel

@Composable
fun MuffinShopApp(
    viewModel: PedidoViewModel = viewModel()
) {
    val navController = rememberNavController()

    val backEntry by navController.currentBackStackEntryAsState()

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val rutaActual = backEntry?.destination?.route

    // Identificar la etapa actual del pedido
    val paso = Paso.entries.firstOrNull {
        it.ruta == rutaActual
    } ?: Paso.SABOR

    val pasos = listOf(
        Paso.SABOR,
        Paso.RELLENO,
        Paso.TOPPINGS,
        Paso.CANTIDAD,
        Paso.CLIENTE,
        Paso.RESUMEN
    )

    val indicePaso = pasos.indexOf(paso)

    Scaffold(
        // Barra superior, como en el ejercicio del profesor
        topBar = {
            if (rutaActual != "inicio" && paso != Paso.EXITO) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 12.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = paso.titulo,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "Paso ${indicePaso + 1} de 6",
                        style = MaterialTheme.typography.labelSmall
                    )

                    LinearProgressIndicator(
                        progress = {
                            (indicePaso + 1) / 6f
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // Sin bottomBar adicional
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "inicio",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // PANTALLA DE INICIO
            composable("inicio") {
                InicioMuffinScreen(
                    onIniciarPedido = {
                        navController.navigate(Paso.SABOR.ruta)
                    }
                )
            }

            // PASO 1: SABOR
            composable(Paso.SABOR.ruta) {
                PantallaConBotones(
                    state = state,
                    paso = Paso.SABOR,
                    onRegresar = {
                        navController.popBackStack()
                    },
                    onContinuar = {
                        navController.navigate(Paso.RELLENO.ruta)
                    }
                ) {
                    SaborScreen(
                        state = state,
                        onSeleccionar = viewModel::seleccionarSabor
                    )
                }
            }

            // PASO 2: RELLENO
            composable(Paso.RELLENO.ruta) {
                PantallaConBotones(
                    state = state,
                    paso = Paso.RELLENO,
                    onRegresar = {
                        navController.popBackStack()
                    },
                    onContinuar = {
                        navController.navigate(Paso.TOPPINGS.ruta)
                    }
                ) {
                    RellenoScreen(
                        state = state,
                        onSeleccionar = viewModel::seleccionarRelleno
                    )
                }
            }

            // PASO 3: TOPPINGS
            composable(Paso.TOPPINGS.ruta) {
                PantallaConBotones(
                    state = state,
                    paso = Paso.TOPPINGS,
                    onRegresar = {
                        navController.popBackStack()
                    },
                    onContinuar = {
                        navController.navigate(Paso.CANTIDAD.ruta)
                    }
                ) {
                    ToppingScreen(
                        state = state,
                        onAlternar = viewModel::alternarTopping
                    )
                }
            }

            // PASO 4: CANTIDAD
            composable(Paso.CANTIDAD.ruta) {
                CantidadScreen(
                    state = state,
                    onCambiarCantidad = viewModel::cambiarCantidad,
                    onContinuar = {
                        navController.navigate(Paso.CLIENTE.ruta)
                    },
                    onRegresar = {
                        navController.popBackStack()
                    }
                )
            }

            // PASO 5: DATOS DEL CLIENTE
            composable(Paso.CLIENTE.ruta) {
                ClientesScreen(
                    state = state,
                    onNombre = viewModel::cambiarNombre,
                    onTelefono = viewModel::cambiarTelefono,
                    onCorreo = viewModel::cambiarCorreo,
                    onContinuar = {
                        navController.navigate(Paso.RESUMEN.ruta)
                    },
                    onRegresar = {
                        navController.popBackStack()
                    }
                )
            }

            // PASO 6: RESUMEN
            composable(Paso.RESUMEN.ruta) {
                ResumenScreen(
                    state = state,
                    onConfirmar = {
                        if (state.esValido(Paso.RESUMEN)) {
                            viewModel.confirmarPedido()

                            navController.navigate(Paso.EXITO.ruta)
                        }
                    },
                    onRegresar = {
                        navController.popBackStack()
                    }
                )
            }

            // PANTALLA FINAL: ÉXITO
            composable(Paso.EXITO.ruta) {
                ExitoScreen(
                    state = state,
                    onNuevoPedido = {
                        viewModel.reiniciar()

                        navController.navigate("inicio") {
                            popUpTo("inicio") {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

// PANTALLA DE BIENVENIDA
@Composable
fun InicioMuffinScreen(
    onIniciarPedido: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "¡Bienvenido a MuffinShop!",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Image(
            painter = painterResource(
                id = R.drawable.muffin_02
            ),
            contentDescription = "Muffins de MuffinShop",
            modifier = Modifier.size(200.dp)
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Crea tu muffin favorito",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "Selecciona el sabor, relleno, toppings y cantidad que prefieras.",
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(
            onClick = onIniciarPedido,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar pedido")
        }
    }
}

// CONTENEDOR CON BOTONES ELEVADOS
@Composable
private fun PantallaConBotones(
    state: PedidoUiState,
    paso: Paso,
    onRegresar: () -> Unit,
    onContinuar: () -> Unit,
    contenido: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        // Contenido principal de cada pantalla
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            contenido()
        }

        // Botones separados de la barra del sistema
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 28.dp
                ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedButton(
                onClick = onRegresar,
                modifier = Modifier.weight(1f)
            ) {
                Text("Regresar")
            }

            Button(
                onClick = onContinuar,
                enabled = state.esValido(paso),
                modifier = Modifier.weight(1f)
            ) {
                Text("Continuar")
            }
        }
    }
}
