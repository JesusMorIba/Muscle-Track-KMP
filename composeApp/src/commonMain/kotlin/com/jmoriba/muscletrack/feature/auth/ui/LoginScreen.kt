package com.jmoriba.muscletrack.feature.auth.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jmoriba.muscletrack.designsystem.component.button.SocialLoginButtons
import com.jmoriba.muscletrack.designsystem.component.section.AuthHeader
import com.jmoriba.muscletrack.designsystem.component.textfield.EmailTextField
import com.jmoriba.muscletrack.designsystem.component.textfield.PasswordTextField
import com.jmoriba.muscletrack.designsystem.theme.LightBackgroundAppColor
import com.jmoriba.muscletrack.di.previewModule
import com.jmoriba.muscletrack.feature.auth.presentation.AuthViewModel
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isAuthenticated) {
        if (uiState.isAuthenticated) {
            onLoginSuccess()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LightBackgroundAppColor)
    ) {
        Scaffold(
            containerColor = Color.Transparent
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AuthHeader()

                Spacer(modifier = Modifier.height(32.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {

                        EmailTextField(
                            value = uiState.email.text,
                            onValueChange = viewModel::updateEmail ,
                            label = "Email Address",
                            errorMessage = uiState.email.error
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        PasswordTextField(
                            value = uiState.password.text,
                            onValueChange = viewModel::updatePassword ,
                            label = "Password",
                            errorMessage = uiState.password.error
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = uiState.rememberMe,
                                    onCheckedChange = viewModel::updateRememberMe,
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = Color(0xFF1A56F0)
                                    )
                                )
                                Text("Remember me", fontSize = 14.sp)
                            }

                            TextButton(onClick = viewModel::forgotPassword) {
                                Text(
                                    text = "Forgot password?",
                                    color = Color(0xFF1A56F0),
                                    fontSize = 14.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = viewModel::login,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF1A56F0)
                            ),
                            enabled = !uiState.isLoading
                        ) {
                            if (uiState.isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = Color.White
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.Login,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Sign In")
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Don't have an account? ",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                            TextButton(
                                onClick = onNavigateToRegister,
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(
                                    text = "Register",
                                    color = Color(0xFF1A56F0),
                                    fontSize = 14.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))


                        SocialLoginButtons(
                            onGoogleClick = { /* TODO */ },
                            onAppleClick = { /* TODO */ }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "By continuing, you agree to our ",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Terms of Service",
                        fontSize = 12.sp,
                        color = Color(0xFF1A56F0)
                    )
                    Text(
                        text = " and ",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Privacy Policy",
                        fontSize = 12.sp,
                        color = Color(0xFF1A56F0)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    PreComposeApp {
        KoinApplication(application = {
            modules(previewModule())
        }) {
            val viewModel = koinViewModel<AuthViewModel>()
            LoginScreen(
                viewModel = viewModel,
                onNavigateToRegister = {},
                onLoginSuccess = {},
            )
        }
    }
}
