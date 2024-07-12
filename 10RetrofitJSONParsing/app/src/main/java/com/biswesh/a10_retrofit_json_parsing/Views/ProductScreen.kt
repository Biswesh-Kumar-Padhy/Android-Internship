package com.biswesh.a10_retrofit_json_parsing.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.biswesh.a10_retrofit_json_parsing.Model.Data.Product
import com.biswesh.a10_retrofit_json_parsing.ViewModels.ProductViewModel

@Composable
fun ProductScreen(productVM: ProductViewModel) {
    val products by productVM.products.observeAsState(emptyList())
    Text(text = "Hello")
    LazyColumn{
        items(products){
            ProductItem(product = it)
        }
    }

}
@Composable
fun ProductItem(product: Product){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
            )
            Text(text = product.title)
        }
    }
}