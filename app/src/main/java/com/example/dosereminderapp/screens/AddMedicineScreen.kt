package com.example.dosereminderapp.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dosereminderapp.api.ProductManager
import com.example.dosereminderapp.api.model.Product

import com.example.dosereminderapp.api.model.Result
import com.example.dosereminderapp.destinations.Destination

/**
 * AddMedicineScreen displays a screen where the user can search for medications by typing the name.
 * After entering the medication name, the user can click "Search" to fetch the list of products.
 * It also displays a list of search results, allowing the user to select a product and navigate to
 * the reminder configuration screen.
 *
 * @param modifier The modifier to be applied to the UI elements of the screen.
 * @param navController The NavController used for navigating to the next screen when a product is selected.
 */
@Composable
fun AddMedicineScreen(modifier: Modifier, navController: NavController){
    val productManager = remember { ProductManager() }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) } // Status for the text entered by the user
    var hasSearched by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Color(red = 200, green = 222, blue = 255)),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Tittle
        Text(
            text = "Add",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(red = 68, green = 140, blue = 252),
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Text field to enter the medication
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Type medication:") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 8.dp),
                trailingIcon = {
                    if (searchQuery.text.isNotEmpty()) {
                        IconButton(onClick = {
                            // Clean up the text and the list of medications
                            searchQuery = TextFieldValue("")
                            productManager.clearProducts()
                            hasSearched = false
                        }) {
                            Icon(Icons.Filled.Clear, contentDescription = "Clear text")
                        }
                    }
                }
            )

            // Search button
            Button(
                onClick = {
                    productManager.searchProducts(searchQuery.text)
                    hasSearched = true // Mark that the search has been performed
                },
                modifier = Modifier
                    .height(70.dp)
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor  = Color(red = 68, green = 140, blue = 252))

            ) {
                Text(text = "Search", color = Color.White, fontSize = 20.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List of products displayed after search
        ProductList(
            products = productManager.productResponse.value,
            hasSearched = hasSearched,
            onProductSelected = { selectedProduct ->
                val strength = selectedProduct.activeIngredients?.firstOrNull()?.strength ?: "Unknown"
                val productName = "${selectedProduct.brandName} - $strength"
                navController.navigate(Destination.ConfigReminder.createRoute(productName))

            }
        )

    }
}

/**
 * ProductList displays a list of products fetched from a search. It shows the products one by one
 * in a scrollable list, allowing the user to select a product to proceed.
 *
 * @param products The list of products to display.
 * @param hasSearched A flag that indicates if a search has been performed.
 * @param onProductSelected A callback function that is called when a product is selected from the list.
 */
@Composable
fun ProductList(
    products: List<Result>,
    hasSearched: Boolean,
    onProductSelected: (Product) -> Unit
) {
    var selectedProductIndex by remember { mutableStateOf(-1) } // -1 means no selection

    when {
        products.isEmpty() && hasSearched -> {
            Text("", modifier = Modifier.padding(16.dp))// No results found.
        }
        products.isNotEmpty() -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                products.forEach { result ->
                    result.products?.let { products ->
                        items(products.size) { index ->
                            val product = products[index]
                            ProductItem(
                                product = product,
                                onClick = {
                                    selectedProductIndex = if (selectedProductIndex == index) -1 else index // Change the selection
                                    onProductSelected(product)
                                },
                                isSelected = selectedProductIndex == index // Determines if the product is selected
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * ProductItem displays a single product within a card. It shows the product's name and strength
 * and highlights the selected product with a different background color.
 *
 * @param product The product to display.
 * @param onClick A callback function that is triggered when the product item is clicked.
 * @param isSelected A boolean indicating whether the product is currently selected.
 */
@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit,  // Handles the click on the item
    isSelected: Boolean
) {
    val brandName = product.brandName ?: "Unknown product"
    val strength = product.activeIngredients?.firstOrNull()?.strength ?: ""

    // Changes the background color if selected
    val backgroundColor = if (isSelected) Color(252, 227, 220) else Color(255, 255, 255)

    // Card to display the product
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp) // Separation between cards
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor // Card background color
        )
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "$brandName - $strength", style = MaterialTheme.typography.titleMedium)

        }
    }
}