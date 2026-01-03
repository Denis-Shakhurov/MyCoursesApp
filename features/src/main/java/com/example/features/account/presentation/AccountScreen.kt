package com.example.features.account.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.core.ui.theme.Dark
import com.example.core.ui.theme.RobotoFontFamily
import com.example.core.ui.theme.White

@Composable
fun AccountScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Экран аккаунта",
            fontSize = 16.sp,
            fontFamily = RobotoFontFamily,
            fontWeight = FontWeight.Medium,
            color = White
        )
    }
}