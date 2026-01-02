package com.example.homeandroid.ui.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.homeandroid.R

object CustomIcons {
    @Composable
    fun Quiz(): Painter = painterResource(id = R.drawable.ic_quiz)
    
    @Composable
    fun Target(): Painter = painterResource(id = R.drawable.ic_target)
    
    @Composable
    fun TrendingUp(): Painter = painterResource(id = R.drawable.ic_trending_up)
    @Composable
    fun Home(): Painter = painterResource(id = R.drawable.ic_home)


    @Composable
    fun TrendingDown(): Painter = painterResource(id = R.drawable.ic_trending_down)
    
    @Composable
    fun BarChart(): Painter = painterResource(id = R.drawable.ic_bar_chart)
}

