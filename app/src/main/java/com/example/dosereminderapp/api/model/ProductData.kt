package com.example.dosereminderapp.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductData(
    @Json(name = "results")
    val results: List<Result>?
)