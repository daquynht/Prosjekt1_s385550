package com.example.prosjekt1_s385550.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prosjekt1_s385550.ui.components.AppTopBar
import androidx.compose.ui.res.stringResource
import com.example.prosjekt1_s385550.R

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
                text = stringResource(id = R.string.about_game),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.about_game_text),
                fontSize = 18.sp,
                lineHeight = 28.sp
            )
        }
    }
}




