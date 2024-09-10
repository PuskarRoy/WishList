package com.example.wishlist

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wishlist.data.WishViewModel

@Composable
fun Navigation(viewModel: WishViewModel = viewModel(), navHostController: NavHostController = rememberNavController()){
    NavHost(navController = navHostController, startDestination = Screen.HomeScreen.route ){
        composable(Screen.HomeScreen.route){
            HomeView(navHostController,viewModel, navigate = {
                navHostController.currentBackStackEntry?.savedStateHandle?.set("id",it)
                navHostController.navigate(Screen.AddScreen.route)
            })

        }
        composable(Screen.AddScreen.route){
            val id = navHostController.previousBackStackEntry?.savedStateHandle?.get<Long>("id")?:0L
            navHostController.currentBackStackEntry?.savedStateHandle?.set("id",0L)
            AddEditView(id = id, viewModel = viewModel, navController = navHostController )

        }
    }
}
