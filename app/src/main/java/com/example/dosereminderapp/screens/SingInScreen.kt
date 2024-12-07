package com.example.dosereminderapp.screens

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dosereminderapp.MainActivity
import com.example.dosereminderapp.R
import com.example.dosereminderapp.SingInActivity
import com.google.firebase.auth.FirebaseAuth

/**
 * Composable function for the Sign-In screen.
 * Displays input fields for email and password, a logo at the top, and a Sign-In button.
 * When the button is clicked, it attempts to authenticate the user with Firebase.
 *
 * @param context Context required for navigation and displaying Toast messages.
 * @param modifier Modifier for customizing the layout.
 */
@Composable
fun SingInScreen (context: Context , modifier: Modifier = Modifier){
    // state level variables
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(null) }
    var keyboardController = LocalSoftwareKeyboardController.current

    Column (
        modifier = modifier
            .fillMaxSize(1f)
            .background(Color(red = 200, green = 222, blue = 255))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.logo_dr),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(180.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit
        )

        TextField(
            value = email,
            onValueChange = {email = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = {Text("Email")},
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
        )

        TextField(
            value = password,
            onValueChange = {password = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = {Text("Password")},
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )

        Button(
            onClick = {
                performsSingIn(email, password, context, keyboardController)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(red = 255, green = 68, blue = 13) // Color del botón
            ),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
        ) {
            Text("Sing In",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White  // Button text color
                )
            )
        }


    }
}

/**
 * Function to perform Sign-In using Firebase Authentication.
 * Validates the email and password with Firebase, navigates to MainActivity on success,
 * and displays a Toast message on success or failure.
 *
 * @param email The email address provided by the user.
 * @param password The password provided by the user.
 * @param context Context required for navigation and displaying Toast messages.
 * @param keyboardController Controller to hide the keyboard after action.
 */
private fun performsSingIn(
    email: String,
    password: String,
    context: Context,
    keyboardController: SoftwareKeyboardController?
){
    val auth = FirebaseAuth.getInstance()

    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener{ task->
            if (task.isSuccessful){
                Toast.makeText(context,"Sing In Successful", Toast.LENGTH_SHORT).show()
                var intent = Intent(context, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("userID", auth.currentUser?.uid)
                context.startActivity(intent)
            }else{
                Toast.makeText(context, "Sing In Failed", Toast.LENGTH_SHORT).show()

            }
            keyboardController?.hide()
        }
}