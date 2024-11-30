package com.example.dosereminderapp.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ActiveIngredient(
    @Json(name = "name")
    val name: String?,
    @Json(name = "strength")
    val strength: String?
)