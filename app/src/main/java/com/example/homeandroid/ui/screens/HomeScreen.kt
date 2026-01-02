package com.example.homeandroid.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homeandroid.R
import com.example.homeandroid.data.repository.AuthRepository
import com.example.homeandroid.ui.icons.CustomIcons
import com.example.homeandroid.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    onNavigateToNotifications: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                actions = {
                    IconButton(onClick = onNavigateToNotifications) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                uiState.errorMessage != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = uiState.errorMessage ?: "Error loading data",
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.retry() }) {
                            Text("Retry")
                        }
                    }
                }
                uiState.dashboardData != null -> {
                    HomeContent(
                        dashboardData = uiState.dashboardData!!,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun HomeContent(
    dashboardData: com.example.homeandroid.data.model.StudentDashboardResponse,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Hello ${dashboardData.student.name}!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = dashboardData.student.className,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HomeMetricCard(
                title = "Availability",
                value = dashboardData.student.availability.status,
                iconPainter = CustomIcons.Home(),
                bgColor = Color(0xFFE8F5E9),
                tintColor = Color(0xFF4CAF50),
                modifier = Modifier.weight(1f)
            )
            HomeMetricCard(
                title = "Quiz",
                value = "${dashboardData.student.quiz.attempts} Attempt",
                iconPainter = CustomIcons.Quiz(),
                bgColor = Color(0xFFFFF3E0),
                tintColor = Color(0xFFFF9800),
                modifier = Modifier.weight(1f)
            )
            HomeMetricCard(
                title = "Accuracy",
                value = dashboardData.student.accuracy.current,
                iconPainter = CustomIcons.Target(),
                bgColor = Color(0xFFFBE9E7),
                tintColor = Color(0xFFFF5722),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Today's Summary",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E5F5)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE1BEE7))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Focused Icon
                Icon(
                    painter = painterResource(id = R.drawable.ic_focused),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    tint = Color.Unspecified
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = dashboardData.todaySummary.mood,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7B1FA2)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "“${dashboardData.todaySummary.description}”",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = { /* Handle video action */ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A1A1A))
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = dashboardData.todaySummary.recommendedVideo.actionText,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Weekly Overview",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEEEEEE))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Quiz Streak Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Quiz Streak", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Icon(painter = painterResource(id = R.drawable.ic_quiz), contentDescription = null, modifier = Modifier.size(32.dp), tint = Color.Unspecified)
                }
                
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val days = listOf("M", "T", "W", "T", "F", "S", "S")
                    dashboardData.weeklyOverview.quizStreak.forEachIndexed { index, day ->
                        val isDone = day.status == "done"
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(if (isDone) Color(0xFF4CAF50) else Color.Transparent)
                                .border(
                                    width = 1.dp,
                                    color = if (isDone) Color(0xFF4CAF50) else Color.LightGray,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isDone) {
                                Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
                            } else {
                                Text(text = days[index % 7], color = Color.Gray, fontSize = 14.sp)
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                Divider(color = Color(0xFFEEEEEE))
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Accuracy", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Icon(painter = painterResource(id = R.drawable.ic_target), contentDescription = null, modifier = Modifier.size(32.dp), tint = Color.Unspecified)
                }
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(text = "68% correct", fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Medium)
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // Custom Progress Bar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFFFEBEE))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.68f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFFF5252))
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                Divider(color = Color(0xFFEEEEEE))
                Spacer(modifier = Modifier.height(24.dp))
                
                // Performance Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Performance by Topic", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Icon(painter = painterResource(id = R.drawable.ic_bar_chart), contentDescription = null, modifier = Modifier.size(32.dp), tint = Color.Unspecified)
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Topic List
                dashboardData.weeklyOverview.performanceByTopic.forEach { topic ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = topic.topic,
                            modifier = Modifier.weight(1f),
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                        Icon(
                            painter = if (topic.trend == "up") CustomIcons.TrendingUp() else CustomIcons.TrendingDown(),
                            contentDescription = null,
                            tint = if (topic.trend == "up") Color(0xFF4CAF50) else Color(0xFFFF5252),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A1A1A))
                ) {
                    Text(text = "More Details", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun HomeMetricCard(
    title: String,
    value: String,
    iconPainter: Painter,
    bgColor: Color,
    tintColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = androidx.compose.foundation.BorderStroke(1.dp, tintColor.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(bgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = iconPainter,
                    contentDescription = title,
                    tint = tintColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                fontSize = 13.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = value,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (title == "Availability") tintColor else Color.Black
            )
        }
    }
}

