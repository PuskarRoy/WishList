package com.example.wishlist

sealed class Screen(val route:String) {
    object HomeScreen:Screen(route = "homescreen")
    object AddScreen:Screen(route = "addscreen")
}