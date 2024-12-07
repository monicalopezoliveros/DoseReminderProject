package com.example.dosereminderapp.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * This data class represents an active ingredient in a product. It includes the name and strength of the ingredient.
 */
@JsonClass(generateAdapter = true)
data class ActiveIngredient(
    @Json(name = "name")
    val name: String?,
    @Json(name = "strength")
    val strength: String?
)