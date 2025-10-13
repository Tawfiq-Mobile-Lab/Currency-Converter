package com.tawfiqdev.currencyconverter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.tawfiqdev.currencyconverter.data.ConversionRepository
import com.tawfiqdev.currencyconverter.data.StaticRateProvider
import com.tawfiqdev.currencyconverter.domain.ConversionUseCase
import com.tawfiqdev.currencyconverter.domain.Currency
import com.tawfiqdev.currencyconverter.util.formatAmount

@Composable
fun ConverterScreen() {
    // Minimal DI inline to keep the starter simple
    val useCase = remember { ConversionUseCase(ConversionRepository(StaticRateProvider())) }

    var from by remember { mutableStateOf(Currency.EUR) }
    var to by remember { mutableStateOf(Currency.USD) }
    var input by remember { mutableStateOf("120.00") }

    val amount = input.toDoubleOrNull() ?: 0.0
    val result = remember(
        from,
        to,
        input
    ) {
        useCase.execute(amount, from, to)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(20.dp)
    ) {
        Text("Convert", style = MaterialTheme.typography.titleMedium)
        Text("Any Currency", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        CardContainer {
            CurrencyPicker(selected = from, onSelect = { from = it })
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Amount") }
            )
            Spacer(Modifier.height(8.dp))
            Text("1 ${'$'}{from.code} = ${"%.2f".format(result.rate)} ${'$'}{to.code}")
        }

        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable { val t = from; from = to; to = t }
                    .padding(12.dp)
            ) { Text("⇅", color = MaterialTheme.colorScheme.onPrimary) }
        }

        CardContainer {
            CurrencyPicker(selected = to, onSelect = { to = it })
            Spacer(Modifier.height(12.dp))
            Text(
                text = formatAmount(result.amount, to),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(8.dp))
            Text("1 ${'$'}{to.code} = ${"%.2f".format(1.0 / result.rate)} ${'$'}{from.code}")
        }
    }
}
