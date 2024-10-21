package com.example.dosereminderapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dosereminderapp.R

@Composable
fun PillBoxScreen(modifier: Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(red = 200, green = 222, blue = 255))
    ){
        Column(

        ){
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Pill Box",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(red = 68, green = 140, blue = 252),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                colors = CardDefaults.cardColors(containerColor = Color(red = 255, green = 255, blue = 255))
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Icon on the left
                    Icon(
                        painter = painterResource(id = R.drawable.ic_capsule),
                        contentDescription = "Medicine Icon",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(10.dp),
                        tint = Color.Unspecified
                    )

                    // Column with information about the medicine
                    Column {
                        Text(text = "Acetaminophen 160 mg/5ml ", fontWeight = FontWeight.Bold)
                        Text(text = "1 Tablet(s)")
                        Text(text = "Every for hours")
                        Text(text = "10:30 AM 2:30 PM 6:30 PM 10:30 PM")
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(1.dp),
                border = BorderStroke(1.dp, Color.LightGray),
                colors = CardDefaults.cardColors(containerColor = Color(red = 255, green = 255, blue = 255))
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Icon on the left
                    Icon(
                        painter = painterResource(id = R.drawable.ic_capsule),
                        contentDescription = "Medicine Icon",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(10.dp),
                        tint = Color.Unspecified
                    )

                    // Column with information about the medicine
                    Column {
                        Text(text = "Salbutamol Inhaler", fontWeight = FontWeight.Bold)
                        Text(text = "2 Puff(s)")
                        Text(text = "Twice a day")
                        Text(text = "8:20 AM 8:20 PM")
                    }
                }
            }
        }
    }
}