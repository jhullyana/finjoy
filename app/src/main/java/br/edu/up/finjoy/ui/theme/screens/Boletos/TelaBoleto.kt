package br.edu.up.finjoy.ui.theme.screens.Adicionar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.edu.up.finjoy.ui.theme.screens.Calendario.CalendarioFixo
import br.edu.up.finjoy.ui.theme.screens.Util.FinjoyTopBar
import br.edu.up.finjoy.ui.theme.screens.Util.TelaDoisBottomBar

object RotaAdicionarBoleto {
    val TELA_LISTAR_BOLETOS_ROTA = "listar_boletos"
    val TELA_INCLUIR_BOLETOS_ROTA = "incluir_boletos"
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TelaBoleto(drawerState: DrawerState,
               navCtrlBottomNav: NavController
){

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

                CalendarioFixo(onDateSelected = { ano, mes, dia ->
                    val dataSelecionada = "$ano-${mes + 1}-$dia"  // Ajusta o mês (adiciona 1, pois é zero-based)
                    navCtrlBottomNav.navigate("t2e/$dataSelecionada")
                })

//                CalendarioFixo(onDateSelected = { (ano, mes dia) ->
//                    navCtrlBottomNav.navigate("t2e/${selectDate}")
//                })

            }
        },
        bottomBar = { TelaDoisBottomBar(navController = navCtrlBottomNav)}
    )
}