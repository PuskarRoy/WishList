package com.example.wishlist.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel (private val repository: WishRepository = Graph.wishRepository): ViewModel(){

    var wishtitle by mutableStateOf("")
    var wishdescription by mutableStateOf("")

    fun onTitleChange(str : String){
        wishtitle = str
    }
    fun ondesChange(str : String){
        wishdescription = str
    }

    lateinit var allWishes : Flow<List<Wish>>

    init {
        viewModelScope.launch {
            allWishes = repository.getAllWishes()
        }
    }

    fun addWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWish(wish)
        }
    }

    fun deleteWish(wish: Wish){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteWish(wish)
        }
    }

    fun updateWish(wish: Wish){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateWish(wish)
        }
    }

    fun getWishById(id:Long):Flow<Wish>{

        return repository.getWishById(id)
    }
}