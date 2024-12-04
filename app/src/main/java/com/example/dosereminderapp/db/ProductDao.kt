package com.example.dosereminderapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dosereminderapp.api.model.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: Product)

    @Query("SELECT * FROM Product WHERE productNumber = :productNumber")
    fun getProductByNumber(productNumber: String): Product?

    @Query("SELECT * FROM Product")
    fun getAllProducts(): List<Product>

    @Update
    fun updateProduct(product: Product)

    @Query("DELETE FROM Product WHERE productNumber = :productNumber")
    fun deleteProductByNumber(productNumber: String)
}
