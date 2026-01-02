package com.example.homeandroid.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Path
import androidx.compose.foundation.Canvas
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homeandroid.R
import com.example.homeandroid.ui.components.WavyShape
import com.example.homeandroid.ui.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    var schoolId by remember { mutableStateOf("") }
    var studentId by remember { mutableStateOf("") }
    var schoolIdError by remember { mutableStateOf<String?>(null) }
    var studentIdError by remember { mutableStateOf<String?>(null) }

    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { error ->
            snackbarHostState.showSnackbar(
                message = error,
                actionLabel = "Dismiss",
                duration = SnackbarDuration.Short
            )
            viewModel.clearError()
        }
    }

    LaunchedEffect(uiState.isLoginSuccessful) {
        if (uiState.isLoginSuccessful) {
            onLoginSuccess()
            viewModel.resetLoginState()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // ... (background elements same)
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 20.dp, y = (-20).dp)
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFC8E6C9))
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = (-30).dp, y = 70.dp)
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF8BBD0))
            )
            Box(
                modifier = Modifier
                    .offset(x = 40.dp, y = 320.dp)
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset(x = (-20).dp, y = 30.dp)
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFD54F))
            )
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Upper Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.2f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(60.dp))
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFC8E6C9)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "PW", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.DarkGray)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Box(modifier = Modifier.width(280.dp).height(200.dp)) {
                        AvatarInCircle(Color(0xFFF8BBD0), Modifier.align(Alignment.TopStart))
                        AvatarInCircle(Color(0xFFB3E5FC), Modifier.align(Alignment.TopEnd))
                        AvatarInCircle(Color(0xFFC8E6C9), Modifier.align(Alignment.BottomCenter).offset(y = (-10).dp))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Welcome to", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Quizzy!", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.ExtraBold)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                // The White Card using WavyShape
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White,
                    shape = WavyShape()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 32.dp, vertical = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Let's Get you Signed in",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        LoginTextField(
                            value = schoolId,
                            onValueChange = { 
                                schoolId = it
                                schoolIdError = if (it.isBlank()) "School ID is required" else null
                            },
                            placeholder = "School ID",
                            isError = schoolIdError != null,
                            errorMessage = schoolIdError
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        LoginTextField(
                            value = studentId,
                            onValueChange = { 
                                studentId = it
                                studentIdError = if (it.isBlank()) "Student ID is required" else null
                            },
                            placeholder = "Student ID",
                            isError = studentIdError != null,
                            errorMessage = studentIdError
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp)
                        .size(width = 160.dp, height = 40.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable {
                            schoolIdError = if (schoolId.isBlank()) "School ID is required" else null
                            studentIdError = if (studentId.isBlank()) "Student ID is required" else null
                            
                            if (schoolIdError == null && studentIdError == null) {
                                val trimmedSchoolId = schoolId.trim()
                                val email = if (trimmedSchoolId.contains("@")) trimmedSchoolId else "$trimmedSchoolId@quizzy.com"
                                viewModel.login(email, studentId.trim())
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Sign in",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.4f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)
        )
    }
}

@Composable
fun AvatarInCircle(color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(90.dp)
            .clip(CircleShape)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.student_man),
            contentDescription = null,
            modifier = Modifier.size(70.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(20.dp),
            isError = isError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isError) Color.Red else Color(0xFFE0E0E0),
                unfocusedBorderColor = if (isError) Color.Red else Color(0xFFE0E0E0),
                focusedContainerColor = Color(0xFFF8F9FA),
                unfocusedContainerColor = Color(0xFFF8F9FA)
            ),
            singleLine = true
        )
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}