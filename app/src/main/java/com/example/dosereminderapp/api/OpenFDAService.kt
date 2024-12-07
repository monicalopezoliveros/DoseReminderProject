package com.example.dosereminderapp.api

import com.example.dosereminderapp.api.model.ProductData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * OpenFDAService defines the API interface for fetching product data
 * from the OpenFDA service using Retrofit.
 */
interface OpenFDAService {
    @GET("drug/drugsfda.json")  // Defines the endpoint route
    fun getBrandName(
        @Query("search") searchQuery: String  // Dynamic search parameter
    ): Call<ProductData>
}