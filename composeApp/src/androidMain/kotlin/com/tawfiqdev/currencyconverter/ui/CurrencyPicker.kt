package com.tawfiqdev.currencyconverter.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.tawfiqdev.currencyconverter.domain.Currency

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyPicker(selected: Currency, onSelect: (Currency) -> Unit, modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selected.code,
            onValueChange = {},
            readOnly = true,
            label = { Text("Currency") },
            modifier = modifier.menuAnchor(),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) }
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            Currency.entries.forEach { c ->
                DropdownMenuItem(text = { Text("${c.symbol}  ${c.code}") }, onClick = {
                    onSelect(c); expanded = false
                })
            }
        }
    }
}
