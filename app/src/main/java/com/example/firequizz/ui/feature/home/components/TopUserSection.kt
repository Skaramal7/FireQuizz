package com.example.firequizz.ui.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firequizz.R
import com.example.firequizz.data.currentUser

@Composable
fun TopUserSection(
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = null,
            Modifier
                .size(55.dp)
                .clickable(onClick = onProfileClick)
            )

        Spacer(modifier = Modifier.width(16.dp))

        Text(text = "Hi, ${currentUser.name}", fontSize = 20.sp, modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .height(40.dp)
                .background(color = colorResource(R.color.navy_blue),
                            shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.garnet),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Text(text = "${currentUser.score}", color = colorResource(R.color.white), fontSize = 16.sp)

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = painterResource(R.drawable.plus),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
    
}

@Preview
@Composable
private fun TopUserSectionPreview() {
    TopUserSection(
        onProfileClick = {}
    )
}