package com.example.prosjekt1_s385550.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun MinDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector = Icons.Filled.Info // Optional icon
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Dialog Icon")
        },
        title = { Text(text = dialogTitle) },
        text = { Text(text = dialogText) },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = onConfirmation) {
                Text("Ja")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Nei")
            }
        }
    )
}


