package com.example.dosereminderapp.api

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.dosereminderapp.api.model.ProductData
import com.example.dosereminderapp.api.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductManager (){
    private var _productResponse = mutableStateOf<List<Result>>(emptyList())

    val productResponse : MutableState<List<Result>> get() = _productResponse

    init {
        getProducts("")
    }

    private fun getProducts(brandName: String){

        val service = Api.retrofitService.getBrandName("products.brand_name:\"$brandName\"")

        service.enqueue(object : Callback<ProductData> {
            override fun onResponse(
                call: Call<ProductData>,
                response: Response<ProductData>
            ) {
                if (response.isSuccessful){
                    Log.i("Data", "Data Loaded")
                    _productResponse.value = response.body()?.results?: emptyList()
                    Log.i("DataStream", _productResponse.toString())
                }
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                Log.d("error", "${t.message}")
            }
        })
    }

    fun searchProducts(brandName: String) {
        getProducts(brandName)
    }

    // Nuevo método para limpiar la lista de productos
    fun clearProducts() {
        _productResponse.value = emptyList() // Limpia la lista de productos
    }

}