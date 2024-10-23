package br.edu.up.finjoy.ui.theme.screens.Calendario

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarioFixo(onDateSelected: (String) -> Unit) {
    var selectedDate by remember { mutableStateOf("") }

    val context = LocalContext.current  // Obtendo o contexto atual
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context, // Passando o contexto correto
        { _, selectedYear, selectedMonth, selectedDay ->
            selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            onDateSelected("adicionar_boleto/$selectedDate")
        },
        year, month, day
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = if (selectedDate.isEmpty()) "Selecione uma data" else "Data selecionada: $selectedDate")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { datePickerDialog.show() }) {
            Text(text = "Escolher Data")
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewCalendarioFixo() {
    CalendarioFixo(onDateSelected = {})
}