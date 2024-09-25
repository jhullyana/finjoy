package br.edu.up.finjoy.ui.theme.screens.Adicionar

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object TelaUm {
    val TELA_ADICIONAR_ROUTE = "t1a"
    val TELA_GRAFICO_ROUTE = "t1b"
    val TELA_EXPORTAR_ROUTE = "t1c"
}

@Composable
fun  AdicionarNavHost(drawerState: DrawerState){
    val navCtrlBottonNav = rememberNavController()
    NavHost(
        navController = navCtrlBottonNav,
        startDestination = TelaUm.TELA_ADICIONAR_ROUTE
    ) {
        composable(TelaUm.TELA_ADICIONAR_ROUTE) {
            TelaAdicionar(drawerState, navCtrlBottonNav)
        }
        composable(TelaUm.TELA_GRAFICO_ROUTE) {
            TelaGrafico(drawerState, navCtrlBottonNav)
        }
        composable(TelaUm.TELA_EXPORTAR_ROUTE) {
            TelaExportar(drawerState, navCtrlBottonNav)
        }
    }

}