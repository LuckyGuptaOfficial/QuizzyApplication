package com.example.homeandroid.ui.components

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class WavyShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val cornerRadius = with(density) { 24.dp.toPx() }
            
            // Start after top-left corner
            moveTo(0f, cornerRadius)
            
            // Top-left corner
            val topLeftRect = androidx.compose.ui.geometry.Rect(0f, 0f, cornerRadius * 2, cornerRadius * 2)
            arcTo(topLeftRect, 180f, 90f, false)
            
            // Top edge
            lineTo(size.width - cornerRadius, 0f)
            
            // Top-right corner
            val topRightRect = androidx.compose.ui.geometry.Rect(size.width - cornerRadius * 2, 0f, size.width, cornerRadius * 2)
            arcTo(topRightRect, 270f, 90f, false)
            
            // Right edge
            lineTo(size.width, size.height)
            
            // Bottom edge with wide, low scoop
            val scoopWidth = size.width * 0.55f
            val scoopHeight = size.height * 0.15f
            val startX = (size.width - scoopWidth) / 2
            
            lineTo(startX + scoopWidth, size.height)
            
            // Flatter circular scoop
            cubicTo(
                startX + scoopWidth - (scoopWidth * 0.2f), size.height,
                startX + scoopWidth - (scoopWidth * 0.3f), size.height - scoopHeight,
                startX + (scoopWidth / 2), size.height - scoopHeight
            )
            
            cubicTo(
                startX + (scoopWidth * 0.3f), size.height - scoopHeight,
                startX + (scoopWidth * 0.2f), size.height,
                startX, size.height
            )
            
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}
