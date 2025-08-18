package com.example.firequizz.ui.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.firequizz.R

@Composable
fun Banner() {
    Image(
        painter = painterResource(R.drawable.banner),
        contentDescription = null,
        modifier = Modifier.padding(5.dp)
    )
}