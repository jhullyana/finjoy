package br.edu.up.finjoy.ui.theme.screens.Conversor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.edu.up.finjoy.ui.theme.screens.Util.FinjoyTopBar

@Composable
fun TelaConversor(drawerState: DrawerState){

    Scaffold(topBar = {
        FinjoyTopBar(drawerState )
    },
        content = { iPad ->
            iPad
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
                ) {
                Text(
                    text = "Tela de conversão de moedas")
                Modifier.padding(30.dp)
            }
        })

    }
