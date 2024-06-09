package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.chat_app.Screens.LoginScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.navigation.compose.composable
import com.example.chat_app.Screens.SignUpScreen
import dagger.hilt.android.lifecycle.HiltViewModel

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
        val vm  = hiltViewModel()
        NavHost(navController = navController, startDestination = DestinationScreens.SignUp.route){

            composable(DestinationScreens.SignUp.route){
                SignUpScreen(navController)
            }
            composable(DestinationScreens.Login.route){
                LoginScreen()
            }

        }

    }
}

