package com.example.prosjekt1_s385550.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prosjekt1_s385550.ui.components.AppTopBar

@Composable
fun AboutGame(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Toppbar med tilbakeknapp
        AppTopBar(
            showBackButton = true,
            onBackClick = onBack
        )

        // Hovedinnhold
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()), // rullbar hvis teksten blir lang
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Om MathBite",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "MathBite er et spennende mattespill hvor du kan bli bedre i matte - én bit om gangen!\n\n" +
                        "Du kan velge hvor mange oppgaver du ønsker å spille med i Preferanser (5, 10 eller 15).\n\n" +
                        "Hver oppgave gir deg poeng, og målet er å få flest riktige svar.\n\n" +
                        "Lykke til og ha det gøy med MathBite!",
                fontSize = 18.sp,
                lineHeight = 28.sp
            )
        }
    }
}




