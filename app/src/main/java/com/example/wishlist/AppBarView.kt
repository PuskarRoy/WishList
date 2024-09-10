package com.example.wishlist

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppBarView(title: String, onBackNav: () -> Unit = {}) {


    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 25.sp,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(24.dp)
            )

        },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.appbarcolor),
        navigationIcon = if (!title.contains("WishList")) {
            {
                IconButton(onClick = { onBackNav() }) {

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        } else {
            null
        }

    )

}