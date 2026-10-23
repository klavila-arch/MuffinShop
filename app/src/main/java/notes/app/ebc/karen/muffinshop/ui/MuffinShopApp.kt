
package notes.app.ebc.karen.muffinshop.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import notes.app.ebc.karen.muffinshop.model.Paso
import notes.app.ebc.karen.muffinshop.ui.screens.SaborScreen
import notes.app.ebc.karen.muffinshop.ui.screens.RellenoScreen
import notes.app.ebc.karen.muffinshop.ui.screens.ToppingScreen
import notes.app.ebc.karen.muffinshop.ui.screens.CantidadScreen
import notes.app.ebc.karen.muffinshop.ui.screens.ClientesScreen
import notes.app.ebc.karen.muffinshop.ui.screens.ResumenScreen
import notes.app.ebc.karen.muffinshop.ui.screens.ExitoScreen
import notes.app.ebc.karen.muffinshop.viewmodel.PedidoViewModel

@Composable
fun MuffinShopApp(
    viewModel: PedidoViewModel = viewModel()
) {
    val navController = rememberNavController()
    val state = viewModel.uiState.collectAsState().value

    Scaffold { contentPadding ->

        NavHost(
            navController = navController,
            startDestination = "Inicio",
            modifier = Modifier.padding(contentPadding)
        ) {

            // 1. PANTALLA DE INICIO
            composable(route = "Inicio") {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Bienvenido a MuffinShop")
                    Text("Personaliza tu muffin paso a paso.")

                    Button(
                        onClick = {
                            viewModel.reiniciar()
                            navController.navigate(Paso.SABOR.ruta)
                        }
                    ) {
                        Text("Comenzar pedido")
                    }
                }
            }

            // 2. SABOR
            composable(route = Paso.SABOR.ruta) {
                Column {
                    SaborScreen(
                        state = state,
                        onSeleccionar = viewModel::seleccionarSabor
                    )

                    Button(
                        onClick = {
                            navController.navigate(Paso.RELLENO.ruta)
                        },
                        enabled = state.esValido(Paso.SABOR)
                    ) {
                        Text("Continuar")
                    }

                    Button(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Text("Regresar")
                    }
                }
            }

            // 3. RELLENO
            composable(route = Paso.RELLENO.ruta) {
                Column {
                    RellenoScreen(
                        state = state,
                        onSeleccionar = viewModel::seleccionarRelleno
                    )

                    Button(
                        onClick = {
                            navController.navigate(Paso.TOPPINGS.ruta)
                        },
                        enabled = state.esValido(Paso.RELLENO)
                    ) {
                        Text("Continuar")
                    }

                    Button(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Text("Regresar")
                    }
                }
            }

            // 4. TOPPINGS
            composable(route = Paso.TOPPINGS.ruta) {
                Column {
                    ToppingScreen(
                        state = state,
                        onAlternar = viewModel::alternarTopping
                    )

                    Button(
                        onClick = {
                            navController.navigate(Paso.CANTIDAD.ruta)
                        },
                        enabled = state.esValido(Paso.TOPPINGS)
                    ) {
                        Text("Continuar")
                    }

                    Button(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Text("Regresar")
                    }
                }
            }

            // 5. CANTIDAD
            composable(route = Paso.CANTIDAD.ruta) {
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


            // 6. CLIENTE
            composable(route = Paso.CLIENTE.ruta) {
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


            // 7. RESUMEN
            composable(route = Paso.RESUMEN.ruta) {
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

            // 8. CONFIRMACIÓN
            composable(route = Paso.EXITO.ruta) {
                ExitoScreen(
                    state = state,
                    onNuevoPedido = {
                        viewModel.reiniciar()

                        navController.navigate("Inicio") {
                            popUpTo("Inicio") {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
