package br.edu.up.finjoy.ui.theme.screens.Util

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.edu.up.finjoy.R
import br.edu.up.finjoy.getColorText
import br.edu.up.finjoy.ui.theme.screens.Adicionar.TelaUm

@Composable
fun TelaUmBottomBar(navController: NavController) {
    NavigationBar(containerColor = Color(0xFF4F7550)) {
        NavigationBarItem(
            selected = true,
            onClick = {
                navController.navigate(TelaUm.TELA_ADICIONAR_ROUTE)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "A",
                    modifier = Modifier.size(40.dp)
                )
            },
            label = { Text(text = "Adicionados") }
        )
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(TelaUm.TELA_EXPORTAR_ROUTE)
            }, icon = {
                Icon(
                    painter = painterResource(id = R.drawable.pdff),
                    contentDescription = "B",
                    modifier = Modifier.size(40.dp),
                )
            },
            label = { Text(text = "Exportar")}
        )
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(TelaUm.TELA_GRAFICO_ROUTE)
            }, icon = {
                Icon(painter = painterResource(id = R.drawable.graph),
                    contentDescription = "C",
                    modifier = Modifier.size(40.dp),
                )

 },
            label = { Text(text = "Gráficos") }
        )
    }
}