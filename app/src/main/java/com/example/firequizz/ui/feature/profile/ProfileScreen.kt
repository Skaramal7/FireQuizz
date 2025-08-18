package com.example.firequizz.ui.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firequizz.R
import com.example.firequizz.ui.feature.auth.AuthViewModel

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.grey))
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(text = "Hi, [username]", fontSize = 20.sp, modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .background(
                            color = colorResource(R.color.navy_blue),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.garnet),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(text = "2400", color = colorResource(R.color.white), fontSize = 16.sp)

                    Spacer(modifier = Modifier.width(16.dp))

                    Image(
                        painter = painterResource(R.drawable.plus),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxSize()
                .padding(bottom = 60.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ){
                TextButton(onClick = {
                    authViewModel.signout()
                },
                    modifier = Modifier.padding(0.dp, 300.dp, 0.dp, 0.dp)) {
                    Text(text = "Sign out", color = colorResource(R.color.orange))
                }
            }

        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    val authViewModel = AuthViewModel()
    ProfileScreen(authViewModel)
}