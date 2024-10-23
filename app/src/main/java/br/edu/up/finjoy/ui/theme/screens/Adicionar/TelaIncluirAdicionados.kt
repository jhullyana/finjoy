package br.edu.up.finjoy.ui.theme.screens.Adicionar

import androidx.compose.foundation.focusable
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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

@Composable
fun TelaIncluirAdicionados(onAdicionarClick: (Adicionar) -> Unit) {
    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var valorGasto by remember { mutableStateOf("") }
    var categoriaSelecionada by remember { mutableStateOf("") }
    val categorias = listOf(
        "Alimentação", "Moradia", "Transporte", "Lazer e Entreterimento",
        "Saúde", "Educação", "Roupas e Acessórios", "Tecnologia",
        "Investimentos", "Outros"
    )

    var expandedCategoria by remember { mutableStateOf(false)}

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Incluir novo gasto",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text(text = "Título")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = descricao,
            onValueChange = { descricao = it},
            label = { Text(text = "Descrição")},
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = valorGasto ,
            onValueChange = { valorGasto = it},
            label = { Text("Valor gasto")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .focusable(true)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // DropMenu para selecionar a categoria

        Box {
            TextButton(onClick = {
                expandedCategoria = !expandedCategoria
            }) {
                Text(text = if (categoriaSelecionada.isNotEmpty())
                categoriaSelecionada else "Selecione a categoria")
            }
            DropdownMenu(
                expanded = expandedCategoria,
                onDismissRequest = {
                    expandedCategoria = false
                }) {
                categorias.forEach { categoria ->
                    DropdownMenuItem(
                        text = { Text(categoria) },
                        onClick = { 
                            categoriaSelecionada = categoria
                            expandedCategoria = false
                        })
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { 
                onAdicionarClick(Adicionar(titulo, descricao))
            }) {
            Text(text = "Adicionar")
        }
    }

}