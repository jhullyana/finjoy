package br.edu.up.finjoy.ui.theme.screens.Boletos


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.edu.up.finjoy.ui.theme.screens.Adicionar.Adicionar
import br.edu.up.finjoy.ui.theme.screens.Util.FinjoyTopBar
import br.edu.up.finjoy.ui.theme.screens.Util.TelaDoisBottomBar
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TelaIncluirBoletos(drawerState: DrawerState,
                       navCtrlBottomNav: NavController,
                       dataSelecionada: String?,
                       onAdicionarClick: (Adicionar) -> Unit) {

    Scaffold(
        topBar = {
            FinjoyTopBar(drawerState)
        },
        content = { iPad ->
            iPad
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Tela de Graficos",
                    Modifier.padding(30.dp),
                    fontSize = 40.sp)

            }
        },
        bottomBar = { TelaDoisBottomBar(navCtrlBottomNav) }
    )
    
    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var valorConta by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var expandedCategoria by remember { mutableStateOf(false) }

    val categorias = listOf(
        "Alimentação",
        "Moradia",
        "Transporte",
        "Lazer e Entreterimento",
        "Saúde",
        "Educação",
        "Roupas e Acessórios",
        "Tecnologia",
        "Investimentos",
        "Outros"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Incluir nova conta",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        dataSelecionada?.let {
            SpacerDefault()
            Text(
                text = "Data escolhida: $it",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }


        SpacerDefault()

        // Campo para o Título
        InputField(
            label = "Título",
            value = titulo,
            onValueChange = { titulo = it }
        )

        SpacerDefault()

        // Campo para a Descrição
        InputField(
            label = "Descrição",
            value = descricao,
            onValueChange = { descricao = it }
        )

        SpacerDefault()

        // Campo para o Valor
//        InputField(
//            label = "Valor da conta",
//            value = valorConta,
//            onValueChange = { valorConta = it },
//            keyboardType = KeyboardType.Number
//        )

        var valorConta by remember { mutableStateOf("") }

        val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

        InputField(
            label = "Valor da conta",
            value = valorConta,
            onValueChange = {
                // Remove caracteres que não são números para evitar erros de formatação
                val valorNumerico = it.replace("[^0-9]".toRegex(), "").toDoubleOrNull() ?: 0.0

                // Formata o valor como moeda
                valorConta = formatador.format(valorNumerico / 100) // Divide por 100 para ajustar a vírgula
            },
            keyboardType = KeyboardType.Number
        )

        SpacerDefault()

        // Menu suspenso para selecionar a categoria
        CategoryDropdownMenu(
            categorias = categorias,
            categoriaSelecionada = categoria,
            onCategoriaSelecionada = { categoria = it },
            expanded = expandedCategoria,
            onExpandChange = { expandedCategoria = it }
        )

        SpacerDefault()

        // Botão Adicionar
        Button(
            onClick = {
                onAdicionarClick(Adicionar(titulo, descricao)) //valorConta
            }
        ) {
            Text(text = "Adicionar")
        }
    }
}



@Composable
fun SpacerDefault() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CategoryDropdownMenu(
    categorias: List<String>,
    categoriaSelecionada: String,
    onCategoriaSelecionada: (String) -> Unit,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit
) {
    Box {
        TextButton(onClick = { onExpandChange(!expanded) }) {
            Text(text = if (categoriaSelecionada.isNotEmpty()) categoriaSelecionada else "Selecione a categoria")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandChange(false) }
        ) {
            categorias.forEach { categoria ->
                DropdownMenuItem(
                    text = { Text(categoria) },
                    onClick = {
                        onCategoriaSelecionada(categoria)
                        onExpandChange(false)
                    }
                )
            }
        }
    }
}
