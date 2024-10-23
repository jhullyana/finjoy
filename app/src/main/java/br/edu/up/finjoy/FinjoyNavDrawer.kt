package br.edu.up.finjoy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import br.edu.up.finjoy.ui.theme.screens.Adicionar.AdicionarNavHost
import br.edu.up.finjoy.ui.theme.screens.Boletos.BoletoNavHost
import br.edu.up.finjoy.ui.theme.screens.Conversor.TelaConversor
import br.edu.up.finjoy.ui.theme.screens.Economia.TelaEconomia

object FinjoyRotas {
    val TELA_ADICIONAR_ROTA = "tela_um"
    val TELA_BOLETOS_ROTA = "tela_dois"
    val TELA_ECONOMIAS_ROTA = "tela_tres"
    val TELA_CONVERSOR_ROTA = "tela_quatro"
}


@Preview(
    device = Devices.PIXEL
)


@Composable
fun FinjoyNavDrawer(){

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed)

    val navCtrlDrawer = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
                DrawerContent(navCtrlDrawer, drawerState)
            
        },
        content = {
            NavHost(
                navController = navCtrlDrawer,
                startDestination = FinjoyRotas.TELA_ADICIONAR_ROTA)
            {
                composable(FinjoyRotas.TELA_ADICIONAR_ROTA) {
                    AdicionarNavHost(drawerState)
                }
                composable(FinjoyRotas.TELA_BOLETOS_ROTA) {
                    BoletoNavHost(drawerState)
                }
                composable(FinjoyRotas.TELA_ECONOMIAS_ROTA) {
                    TelaEconomia(drawerState)
                }
                composable(FinjoyRotas.TELA_CONVERSOR_ROTA) {
                    TelaConversor(drawerState )
                }

            }
        }
        )
}

@Composable
private fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState
) {

    val coroutineScope = rememberCoroutineScope()

    val currentBack by navController.currentBackStackEntryAsState()
    val rotaAtual = currentBack?.destination?.route?: FinjoyRotas.TELA_ADICIONAR_ROTA

    val RotaUm = rotaAtual == FinjoyRotas.TELA_ADICIONAR_ROTA
    val RotaDois = rotaAtual == FinjoyRotas.TELA_BOLETOS_ROTA
    val RotaTres = rotaAtual == FinjoyRotas.TELA_ECONOMIAS_ROTA
    val RotaQuatro = rotaAtual == FinjoyRotas.TELA_CONVERSOR_ROTA

    Column (
        modifier = Modifier
            .width(300.dp)
            .background(Color.White)
            .padding(30.dp)
            .fillMaxHeight()
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo do App",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 20.dp)
        )

        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(RotaUm)
            ),
            onClick = {
                navController.navigate(FinjoyRotas.TELA_ADICIONAR_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clip(RoundedCornerShape(8.dp))) {
            Icon(
                painter = painterResource(id = R.drawable.checklist),
                contentDescription = "c" ,
                modifier = Modifier.size(40.dp),
                tint = getColorText(RotaUm)
            )
            Text(text = "Gastos", fontSize = 30.sp,
                color = getColorText(RotaUm)
            )

        }

        Spacer(modifier = Modifier.height(30.dp))

        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(RotaDois)),
                onClick = { 
                    navController.navigate(FinjoyRotas.TELA_BOLETOS_ROTA)
                    coroutineScope.launch { 
                        drawerState.close()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(8.dp))) {
                Icon(painter = painterResource(id = R.drawable.boleto),
                    contentDescription = "boleto",
                    modifier = Modifier.size(40.dp),
                    tint = getColorText(RotaDois)
                )
                Text(text = "Boletos", fontSize = 30.sp,
                    color = getColorText(RotaDois))

                 }

        Spacer(modifier = Modifier.height(30.dp))

        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(RotaTres)),
            onClick = {
                navController.navigate(FinjoyRotas.TELA_ECONOMIAS_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Icon(painter = painterResource(id = R.drawable.porco),
                contentDescription = "economia",
                modifier = Modifier.size(40.dp),
                tint = getColorText(RotaTres)
            )
            Text(text = "Economias", fontSize = 30.sp,
                color = getColorText(RotaTres))

        }

        Spacer(modifier = Modifier.height(30.dp))

        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(RotaQuatro)),
            onClick = {
                navController.navigate(FinjoyRotas.TELA_CONVERSOR_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Icon(painter = painterResource(id = R.drawable.conversor),
                contentDescription = "conversor",
                modifier = Modifier.size(40.dp),
                tint = getColorText(RotaDois)
            )
            Text(text = "Conversor de moedas", fontSize = 30.sp,
                color = getColorText(RotaQuatro))

        }

    }
}

fun getColorMenu(Selecionada: Boolean): Color{
    if (Selecionada){
        return Color(0xFF4F7550)
    } else{
        return Color.Transparent
    }
}

fun getColorText(Selecionada: Boolean): Color {
    if(Selecionada){
        return Color.Black
    } else{
        return Color.DarkGray
    }
}