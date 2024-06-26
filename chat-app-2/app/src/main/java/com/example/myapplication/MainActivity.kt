package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.chat_app.Screens.LoginScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.navigation.compose.composable
import com.example.myapplication.screens.SignUpScreen

sealed class DestinationScreens(var route : String){
    object SignUp :DestinationScreens("signUp")
    object Login :DestinationScreens("login")
    object Profile :DestinationScreens("profile")
    object ChatList :DestinationScreens("chatList")
    object SingleChat :DestinationScreens("singleChat/{chatId}"){
        fun createRoute(id : String) = "singleChat/$id"
    }
    object StatusList :DestinationScreens("StatusList")
    object SingleStatus :DestinationScreens("singleStatus/{userId}"){
        fun createRoute(userid : String) = "singleStatus/$userid"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatAppNavigation()
                }
            }
        }
    }
    @Composable
    fun ChatAppNavigation(){
        val navController = rememberNavController()
        val vm  = hiltViewModel<LCViewModel>()
        NavHost(navController = navController, startDestination = DestinationScreens.SignUp.route){

            composable(DestinationScreens.SignUp.route){
                SignUpScreen(navController, vm)
            }
            composable(DestinationScreens.Login.route){
                LoginScreen()
            }

        }

    }
}

