package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.WishListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WishListDao {
    @Query("SELECT * FROM wishlist_items")
    fun getAllWishlistItems(): Flow<List<WishListEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM wishlist_items WHERE productId = :productId)")
    suspend fun isWishlisted(productId: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: WishListEntity)

    @Delete
    suspend fun delete(item: WishListEntity)

    @Query("DELETE FROM wishlist_items WHERE productId = :productId")
    suspend fun deleteById(productId: Int)
}
