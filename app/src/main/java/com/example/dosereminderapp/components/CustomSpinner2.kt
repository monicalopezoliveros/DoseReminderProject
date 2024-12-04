package com.example.dosereminderapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomSpinner2(
    items: Array<String>,  // List of items for the spinner
    width: Dp = 220.dp,  // Width of the spinner
    height: Dp = 64.dp, // Height of the spinner
    backgroundColor: Color = Color.White, // Background color for the spinner
    fontSize: TextUnit = 20.sp,  // Font size of the text
    borderColor: Color = Color(199, 222, 254), // Border color
    labelSpinner: String,
    modifier: Modifier = Modifier
) {
    var selectedText by remember { mutableStateOf(value = "") } // Holds selected item text
    var isExpanded by remember { mutableStateOf(value = false) } // Controls dropdown visibility
    var textFieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }
    var dropdownPositionY by remember { mutableStateOf(0f) }
    val density = LocalDensity.current

    OutlinedTextField(
        value = selectedText,
        onValueChange = { selectedText = it },
        label = { Text(text = labelSpinner) },
        enabled = false, // Disable editing
        readOnly = true, // Make text field read-only
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.ArrowDropDown, // Dropdown arrow icon
                contentDescription = "Dropdown arrow",
                modifier = Modifier
                    .width(44.dp)
                    .height(height)
            )
        },
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = fontSize,  // Set font size
            fontWeight = FontWeight.Normal
        ),
        colors = TextFieldDefaults.colors(
            disabledIndicatorColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        modifier = Modifier
            .height(height)
            .clickable { isExpanded = true } // Open dropdown on click
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(10.dp))
            .width(width)
            .onGloballyPositioned { coordinates ->
                val position = coordinates.positionInWindow()
                dropdownPositionY = position.y + coordinates.size.height
            }
    )

    DropdownMenu(
        expanded = isExpanded, // Show or hide the dropdown
        onDismissRequest = { isExpanded = false },
        offset = DpOffset(0.dp, with(density) { dropdownPositionY.toDp() }),
        modifier = Modifier
            .width(width)
            .background(backgroundColor)
    ) {
        LazyColumn(
            modifier = Modifier.height(200.dp) // Adjust height for the dropdown list
        ) {
            items(items.size) { index ->
                DropdownMenuItem(
                    text = { Text(text = items[index], fontSize = fontSize) },
                    onClick = {
                        isExpanded = false
                        selectedText = items[index]
                    }
                )
            }
        }
    }
}
