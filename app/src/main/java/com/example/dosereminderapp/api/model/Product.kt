package com.example.dosereminderapp.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * This data class represents a product in the database, including its details such as product number, brand name,
 * active ingredients, and other related information. It is used to hold the product data from the API response.
 */
@JsonClass(generateAdapter = true)
@Entity
data class Product(
    @PrimaryKey @Json(name = "product_number")
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