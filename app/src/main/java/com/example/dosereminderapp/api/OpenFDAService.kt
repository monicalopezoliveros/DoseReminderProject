package com.example.dosereminderapp.api

import com.example.dosereminderapp.api.model.ProductData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenFDAService {
    @GET("drug/drugsfda.json")  // Definir la ruta del endpoint
    fun getBrandName(
        @Query("search") searchQuery: String  // Parámetro de búsqueda dinámico
    ): Call<ProductData>
}