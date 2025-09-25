package com.example.prosjekt1_s385550.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prosjekt1_s385550.PrefViewModel
import com.example.prosjekt1_s385550.R
import com.example.prosjekt1_s385550.ui.components.AppTopBar
import com.example.prosjekt1_s385550.ui.theme.ButtonGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(
    prefViewModel: PrefViewModel,
    onStartClick: () -> Unit,
    onPrefClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(title = "MathBite")
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Bakgrunnsbilde
            Image(
                painter = painterResource(id = R.drawable.bakgrunnsbilde),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .scale(1.2f) // 1.0 = original, > 1.0 = zoom inn
            )

            // Knapper
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val buttonModifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)

                // Start-knapp
                Button(
                    onClick = onStartClick,
                    modifier = buttonModifier,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen)
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = "Start",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Start spill", fontSize = 24.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Preferanser-knapp
                Button(
                    onClick = onPrefClick,
                    modifier = buttonModifier,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Preferanser",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Preferanser", fontSize = 24.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Om spillet-knapp
                Button(
                    onClick = onAboutClick,
                    modifier = buttonModifier,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonGreen)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Om spillet",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Om spillet", fontSize = 24.sp, color = Color.White)
                }
            }
        }
    }
}
