package com.example.dosereminderapp.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * This data class represents the response from the API containing a list of product results.
 * It holds a list of `Result` objects, which represent individual products.
 */
@JsonClass(generateAdapter = true)
data class ProductData(
    @Json(name = "results")
    val results: List<Result>?
)