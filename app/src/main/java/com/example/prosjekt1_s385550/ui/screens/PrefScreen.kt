package com.example.prosjekt1_s385550.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prosjekt1_s385550.PrefViewModel
import com.example.prosjekt1_s385550.R
import com.example.prosjekt1_s385550.ui.components.AppTopBar
import com.example.prosjekt1_s385550.ui.theme.ButtonGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrefScreen(
    prefViewModel: PrefViewModel,
    onBack: () -> Unit
) {
    val savedPref = prefViewModel.hentPref() ?: "5"
    var selectedValue by remember { mutableStateOf(savedPref) }

    val options = listOf("5", "10", "15")

    Box(modifier = Modifier.fillMaxSize()) {
        // Bakgrunnsbilde dekker HELE skjermen
        Image(
            painter = painterResource(id = R.drawable.bakgrunnsbilde),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Scaffold(
            topBar = {
                AppTopBar(
                    title = "Preferanser",
                    showBackButton = true,
                    onBackClick = onBack
                )
            },
            containerColor = Color.Transparent //
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Velg antall oppgaver:",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Radioknapper
                options.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = selectedValue == option,
                            onClick = { selectedValue = option },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = ButtonGreen,
                                unselectedColor = Color.Black
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = option,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Lagre-knapp
                Button(
                    onClick = {
                        prefViewModel.settPref(selectedValue)
                        onBack()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(
                        text = "Lagre",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
