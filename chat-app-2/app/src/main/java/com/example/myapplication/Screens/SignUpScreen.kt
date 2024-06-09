package com.example.chat_app.Screens

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.myapplication.DestinationScreens

@Composable
fun SignUpScreen ( navController: NavController){
    Text(text = "navigate to other",
        modifier = Modifier.clickable {
            navController.navigate(DestinationScreens.Login.route)
        })
}