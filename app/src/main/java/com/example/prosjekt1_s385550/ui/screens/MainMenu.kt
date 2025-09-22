package com.example.prosjekt1_s385550.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prosjekt1_s385550.PrefViewModel
import com.example.prosjekt1_s385550.R
import com.example.prosjekt1_s385550.ui.theme.ButtonGreen

@Composable
fun MainMenu(
    prefViewModel: PrefViewModel,
    onStartClick: () -> Unit,
    onPrefClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Bakgrunnsbilde
        Image(
            painter = painterResource(id = R.drawable.bakgrunnsbilde),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Knapper og tittel
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tittel med skygge
            Text(
                text = "Mattespill",
                fontSize = 45.sp,
                color = ButtonGreen,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 110.dp)
            )

            val buttonModifier = Modifier
                .fillMaxWidth()
                .height(80.dp)

            Button(
                onClick = onStartClick,
                modifier = buttonModifier,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen)
            ) {
                Text(text = "Start spill", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onPrefClick,
                modifier = buttonModifier,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen)
            ) {
                Text(text = "Preferanser", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onAboutClick,
                modifier = buttonModifier,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen)
            ) {
                Text(text = "Om spillet", fontSize = 24.sp)
            }
        }
    }
}
