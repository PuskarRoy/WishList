package com.example.wishlist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    @Insert
    abstract suspend fun addWish(wish: Wish)

    @Delete
    abstract suspend fun deleteWish(wish: Wish)

    @Update
    abstract suspend fun updateWish(wish: Wish)

    @Query("Select * From Wishtable")
    abstract fun getAllWishes():Flow<List<Wish>>

    @Query("Select * From Wishtable Where id = :id")
    abstract fun getWish(id:Long):Flow<Wish>
}