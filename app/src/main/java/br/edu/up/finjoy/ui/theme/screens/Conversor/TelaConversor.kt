package br.edu.up.finjoy.ui.theme.screens.Conversor

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.up.finjoy.ui.theme.screens.Util.FinjoyTopBar
import br.edu.up.finjoy.ui.theme.verdeDiscreto

@Composable
fun TelaConversor(drawerState: DrawerState) {

    var valorInput by remember { mutableStateOf("") }
    var moedaOrigem by remember { mutableStateOf("USD") }
    var moedaDestino by remember { mutableStateOf("BRL") }
    var resultado by remember { mutableStateOf("") }

    // Lista de moedas
    val moedas = listOf("USD", "BRL", "EUR", "GBP")

    Scaffold(
        topBar = {
            FinjoyTopBar(drawerState) // Substitua com sua função de TopBar
        },
        content = { iPad ->
            iPad
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Conversor de Moedas", fontSize = 24.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = valorInput,
                    onValueChange = { valorInput = it },
                    label = { Text("Valor") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions(onDone = { /* Ação ao finalizar entrada */ }),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusable(true) // Permite foco para interação

                )

                Spacer(modifier = Modifier.height(16.dp))

                // Controle para expandir o DropdownMenu
                var expandedOrigem by remember { mutableStateOf(false) }
                var expandedDestino by remember { mutableStateOf(false) }

                // Seleção da moeda de origem
                Box {
                    TextButton(onClick = { expandedOrigem = !expandedOrigem }) {
                        Text(text = "Moeda Origem: $moedaOrigem")
                    }
                    DropdownMenu(
                        expanded = expandedOrigem,
                        onDismissRequest = { expandedOrigem = false }
                    ) {
                        moedas.forEach { moeda ->
                            DropdownMenuItem(
                                text = { Text(moeda, color = verdeDiscreto) },
                                onClick = {
                                    moedaOrigem = moeda
                                    expandedOrigem = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Seleção da moeda de destino
                Box {
                    TextButton(onClick = { expandedDestino = !expandedDestino }) {
                        Text(text = "Moeda Destino: $moedaDestino")
                    }
                    DropdownMenu(
                        expanded = expandedDestino,
                        onDismissRequest = { expandedDestino = false }
                    ) {
                        moedas.forEach { moeda ->
                            DropdownMenuItem(
                                text = { Text(moeda) },
                                onClick = {
                                    moedaDestino = moeda
                                    expandedDestino = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botão para converter
                Button(onClick = {
                    if (valorInput.isNotEmpty()) {
                        val valor = valorInput.toDoubleOrNull() ?: 0.0
                        resultado = converterMoeda(valor, moedaOrigem, moedaDestino).toString()
                    }
                }) {
                    Text("Converter")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Exibe o resultado da conversão
                Text(text = "Resultado: $resultado")
            }
        }
    )
}

// Função para converter moedas (ainda um exemplo básico)
fun converterMoeda(valor: Double, moedaOrigem: String, moedaDestino: String): Double {
    val taxaConversao = when (moedaOrigem) {
        "USD" -> if (moedaDestino == "BRL") 5.0 else 1.0
        "BRL" -> if (moedaDestino == "USD") 0.2 else 1.0
        "EUR" -> if (moedaDestino == "USD") 1.1 else 1.0
        "GBP" -> if (moedaDestino == "USD") 1.3 else 1.0
        else -> 1.0
    }
    return valor * taxaConversao
}

