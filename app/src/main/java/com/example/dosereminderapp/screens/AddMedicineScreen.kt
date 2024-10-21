package com.example.dosereminderapp.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AddMedicineScreen(modifier: Modifier){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color(red = 200, green = 222, blue = 255)),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        // Títle
        Text(
            text = "Add",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(red = 68, green = 140, blue = 252),
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Text field to enter the medication
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Status for text field
            var textState by remember { mutableStateOf(TextFieldValue("")) }

            // Text field
            BasicTextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(16.dp)
                    ) {
                        innerTextField()
                    }
                }
            )

            // Search button
            Button(
                onClick = { /* */ },
                modifier = Modifier
                    .background(Color(red = 200, green = 222, blue = 255))
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Search", color = Color.White)
            }
        }
    }
}
