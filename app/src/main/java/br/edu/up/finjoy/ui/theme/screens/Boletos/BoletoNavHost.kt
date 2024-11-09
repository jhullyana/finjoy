 package br.edu.up.finjoy.ui.theme.screens.Boletos

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.finjoy.ui.theme.screens.Adicionar.TelaBoleto

 object TelaDois{
    val tela_boleto_route = "t2a"
    val tela_pagos_route = "t2b"
    val tela_pendentes_route = "t2c"
    val tela_graficos_route = "t2d"
     val tela_incluir_route = "t2e/{dataSelecionada}"
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BoletoNavHost(drawerState: DrawerState){
    val navCtrlBottomNav = rememberNavController()
    NavHost(
        navController = navCtrlBottomNav,
        startDestination = TelaDois.tela_boleto_route) {
        composable(TelaDois.tela_boleto_route) {
            TelaBoleto(drawerState, navCtrlBottomNav)
        }

        composable(TelaDois.tela_pagos_route) {
            TelaPagos (drawerState, navCtrlBottomNav)
        }
        composable(TelaDois.tela_pendentes_route) {
            TelaPendentes (drawerState, navCtrlBottomNav)
        }
        composable(TelaDois.tela_graficos_route) {
            TelaGraficos (drawerState, navCtrlBottomNav)
        }
        //val tela_incluir_route = "t2e/{dataSelecionada}"
        composable(TelaDois.tela_incluir_route) { request ->
            val dataSelecionada = request.arguments?.getString("dataSelecionada")
            Log.i("teste", dataSelecionada!!)
            TelaIncluirBoletos(drawerState, navCtrlBottomNav, dataSelecionada, onAdicionarClick = { item ->
                Log.i("Adicionar", "Item adicionado:  $item")

                // Navega de volta para a tela TelaBoleto após adicionar
                navCtrlBottomNav.popBackStack(TelaDois.tela_boleto_route, inclusive = false)
            })


        }
    }
}