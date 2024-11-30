package com.example.dosereminderapp.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "product_number")
    val productNumber: String?,
    @Json(name = "reference_drug")
    val referenceDrug: String?,
    @Json(name = "brand_name")
    val brandName: String?,
    @Json(name = "active_ingredients")
    val activeIngredients: List<ActiveIngredient>?,
    @Json(name = "reference_standard")
    val referenceStandard: String?,
    @Json(name = "dosage_form")
    val dosageForm: String?,
    @Json(name = "route")
    val route: String?,
    @Json(name = "marketing_status")
    val marketingStatus: String?,
    @Json(name = "te_code")
    val teCode: String?
)