package com.example.wishlist.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import javax.inject.Singleton


@Module
@Singleton
object Graph {

    lateinit var dataBase:WishDataBase

    val wishRepository by lazy {
        WishRepository(dataBase.getWishDao())
    }

    fun provider(context: Context){
        dataBase = Room.databaseBuilder(context,WishDataBase::class.java,"WishList.db").build()
    }
}