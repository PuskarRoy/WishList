package com.example.wishlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlist.data.Wish
import com.example.wishlist.data.WishViewModel

@Composable
fun AddEditView(id: Long, viewModel: WishViewModel, navController: NavController) {


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBarView(
                title = if (id == 0L) "Add Wish" else "Update Wish",
                onBackNav = { navController.navigateUp() })
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            wishListTexField(
                label = "Title",
                value = viewModel.wishtitle,
                onValueChange = { viewModel.onTitleChange(it) })

            Spacer(modifier = Modifier.height(10.dp))

            wishListTexField(
                label = "Description",
                value = viewModel.wishdescription,
                onValueChange = { viewModel.ondesChange(it) })

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                if (viewModel.wishtitle.isNotEmpty() && viewModel.wishdescription.isNotEmpty()) {
                    if (id != 0L) {
                        viewModel.updateWish(
                            Wish(
                                id = id,
                                title = viewModel.wishtitle.trim(),
                                description = viewModel.wishdescription.trim()
                            )
                        )
                    } else {
                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishtitle.trim(),
                                description = viewModel.wishdescription.trim()
                            )
                        )

                    }
                    navController.navigateUp()
                    viewModel.wishtitle = ""
                    viewModel.wishdescription = ""


                }


            }, modifier = Modifier.fillMaxWidth()) {
                Text(text = if (id == 0L) "Add Wish" else "Update Wish")
            }
        }

    }
}


@Composable
fun wishListTexField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )

}
