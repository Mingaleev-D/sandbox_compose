package com.example.sandbox_compose.ui.pages.home_products.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sandbox_compose.R

@Composable
fun ProfileHeader() {
    Box(
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        Row(
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Image(
                   painter = painterResource(id = R.drawable.ic_person_24),
                   contentDescription = null,
                   modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                       text = "Hello,", style = MaterialTheme.typography.bodySmall
                )
                Text(
                       text = "John Doe",
                       style = MaterialTheme.typography.titleMedium,
                       fontWeight = FontWeight.SemiBold
                )
            }
        }
        Image(
               painter = painterResource(id = R.drawable.ic_notifications_24),
               contentDescription = null,
               modifier = Modifier
                   .size(48.dp)
                   .align(Alignment.CenterEnd)
                   .clip(CircleShape)
                   .background(Color.LightGray.copy(alpha = 0.3f))
                   .padding(8.dp),
               contentScale = ContentScale.Inside
        )
    }
}
