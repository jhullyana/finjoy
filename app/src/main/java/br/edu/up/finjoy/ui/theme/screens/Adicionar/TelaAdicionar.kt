package br.edu.up.finjoy.ui.theme.screens.Adicionar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.finjoy.ui.theme.screens.Util.FinjoyTopBar
import br.edu.up.finjoy.ui.theme.screens.Util.TelaUmBottomBar

object AdicionarRota {
    val TELA_LISTAR_ADICIONADOS_ROTA = "listar_adicionados"
    val TELA_INCLUIR_ADICIONADOS_ROTA = "incluir_adicionados"
}


@Composable
fun TelaAdicionar(
    drawerState: DrawerState,
    navCtrlBottomNav: NavController
) {

    val adicionados = remember { mutableStateListOf<Adicionar>()
    }

    adicionados.addAll(listOf(
        Adicionar(titulo = "Compras dia 05/09",
            descricao = "1.000"
            ),
        Adicionar(titulo = "Lanche",
            descricao = "R$45,00"
        ),
        Adicionar(titulo = "Compras dia 25/09",
            descricao = "R$450,00"
        )
    ))

    val navCtrlAdicionados = rememberNavController()


    Scaffold(
        topBar = { FinjoyTopBar(drawerState) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Meus gastos",
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp)
                        .background(Color.LightGray)
                        .align(alignment = Alignment.CenterHorizontally)
                        .border(2.dp, Color.Red),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,


                )
            }
            NavHost(
                navController = navCtrlAdicionados,
                startDestination = AdicionarRota.TELA_LISTAR_ADICIONADOS_ROTA)
            {
                composable(AdicionarRota.TELA_LISTAR_ADICIONADOS_ROTA) {
                    TelaListagemAdicionados(adicionados)
                }
                composable(AdicionarRota.TELA_INCLUIR_ADICIONADOS_ROTA) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Spacer(modifier = Modifier.height(200.dp))
                        Text(text = "TELA DE INCLUIR GASTOS")
                    }

                }
            }


        },
        floatingActionButton = { FloatButton{ navCtrlAdicionados.navigate(AdicionarRota.TELA_INCLUIR_ADICIONADOS_ROTA) } },
        bottomBar = { TelaUmBottomBar(navCtrlBottomNav) }
    )
}

@Composable
private fun TelaListagemAdicionados(adicionados: SnapshotStateList<Adicionar>) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(adicionados) { adicionar ->
                AdicionarItem(adicionar)


            }
        }
    }

@Composable
fun AdicionarItem(adicionar: Adicionar){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            Text(
                text = adicionar.titulo,
                style = MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = adicionar.descricao,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray)
        }
    }
}


data class Adicionar(
    var titulo: String,
    var descricao: String,
    var concluido: Boolean = false,
    var id: Int? = null
)


@Composable
fun FloatButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "+"
        )
    }
}




