package com.example.features.auth.presentation

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.R
import com.example.core.ui.theme.Blue
import com.example.core.ui.theme.Dark
import com.example.core.ui.theme.DarkGrey
import com.example.core.ui.theme.Green
import com.example.core.ui.theme.Orange
import com.example.core.ui.theme.Orange1
import com.example.core.ui.theme.RobotoFontFamily
import com.example.core.ui.theme.White

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LoginContent(
        state = state,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = {
            viewModel.onLoginClick()
            onLoginSuccess()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginContent(
    state: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Dark)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 32.dp)
                .align(Alignment.Start),
            text = "Вход",
            fontFamily = RobotoFontFamily,
            fontWeight = FontWeight.Normal,
            color = White,
            fontSize = 28.sp
        )

        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Emal",
            fontFamily = RobotoFontFamily,
            fontWeight = FontWeight.Medium,
            color = White,
            fontSize = 16.sp
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.email,
            onValueChange = onEmailChange,
            label = { Text("example@gmail.com") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(color = White),
            shape = RoundedCornerShape(32.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,

                focusedContainerColor = DarkGrey,
                unfocusedContainerColor = DarkGrey,

                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,

                cursorColor = White,

                focusedTextColor = White,
                unfocusedTextColor = White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Пароль",
            fontFamily = RobotoFontFamily,
            fontWeight = FontWeight.Medium,
            color = White,
            fontSize = 16.sp
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state.password,
            onValueChange = onPasswordChange,
            label = { Text("Введите пароль") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(color = White),
            shape = RoundedCornerShape(32.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,

                focusedContainerColor = DarkGrey,
                unfocusedContainerColor = DarkGrey,

                focusedLabelColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,

                cursorColor = White,

                focusedTextColor = White,
                unfocusedTextColor = White
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .background(Green),
            onClick = onLoginClick,
            enabled = state.isLoginEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Green,
                disabledContainerColor = Green.copy(alpha = 0.5f),
                contentColor = White,
                disabledContentColor = White.copy(alpha = 0.5f)
            ),
        ) {
            Text(
                text = "Вход",
                color = White
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Нет аккаунта?",
                fontSize = 12.sp,
                color = White
            )
            TextButton(
                onClick = {},
                enabled = false,
            ) {
                Text(
                    text = "Регистрация",
                    fontSize = 12.sp,
                    color = Green
                )
            }

            TextButton(
                onClick = {},
                enabled = false,
            ) {
                Text(
                    text = "Забыл пароль",
                    fontSize = 12.sp,
                    color = Green
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = White,
            thickness = 1.dp
        )
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val context = LocalContext.current
            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(32.dp))
                    .background(Blue),
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, "https://vk.com/".toUri())
                    context.startActivity(intent)
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_vk),
                    contentDescription = "ВКонтакте",
                    tint = White
                )
            }

            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(32.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Orange,
                                Orange1
                            )
                        )
                    ),
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, "https://ok.ru/".toUri())
                    context.startActivity(intent)
                }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_ok),
                    contentDescription = "Одноклассники",
                    tint = White
                )
            }
        }
    }
}
