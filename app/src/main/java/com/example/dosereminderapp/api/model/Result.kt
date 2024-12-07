package com.example.dosereminderapp.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * This data class represents a single result returned by the API. It contains a list of `Product` objects.
 * Each result may have multiple products associated with it.
 */
@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "products")
    val products: List<Product>?
)