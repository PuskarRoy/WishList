package com.example.wishlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlist.data.Wish
import com.example.wishlist.data.WishViewModel

@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel,
    navigate: (id: Long) -> Unit = {}
) {

    val wishList = viewModel.allWishes.collectAsState(initial = listOf())


    Scaffold(topBar = { AppBarView(title = "WishList") }, floatingActionButton = {
        FloatingActionButton(
            onClick = { navController.navigate(Screen.AddScreen.route) },
            modifier = Modifier.padding(20.dp),
            contentColor = Color.White,
            backgroundColor = Color.Black
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White)
        }
    }) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            items(wishList.value) {
                WishItem(wish = it, onclick = {
                    navigate(it.id)

                    viewModel.wishtitle = it.title
                    viewModel.wishdescription = it.description

                }, ondelete = {
                    viewModel.deleteWish(it)
                })
            }
        }
    }
}

@Composable
fun WishItem(wish: Wish, onclick: () -> Unit,ondelete:()->Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onclick() },
        colors = CardColors(Color.DarkGray, Color.White, Color.White, Color.White)
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
                modifier = Modifier
                    .padding(5.dp).wrapContentHeight().width(300.dp)

            ) {
                Text(text = wish.title, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = wish.description)
            }

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                IconButton(onClick = { ondelete() }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription =null )
                }
            }
        }
    }
}
