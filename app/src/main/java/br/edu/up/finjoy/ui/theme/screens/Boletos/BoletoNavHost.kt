package br.edu.up.finjoy.ui.theme.screens.Boletos

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.up.finjoy.ui.theme.screens.Adicionar.TelaBoleto

object TelaDois{
    val tela_boleto_route = "t2a"
    val tela_pagos_route = "t2b"
    val tela_pendentes_route = "t2c"
    val tela_graficos_route = "t2d"
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
    }
}