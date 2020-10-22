package com.servicetitan.servicetitankmm.stAndroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.servicetitan.servicetitankmm.shared.model.PricebookItem
import com.servicetitan.servicetitankmm.shared.PricebookManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContentView()
        }
    }
}

@Composable
@Preview
fun MainContentView() {
    val pricebookState = mutableStateOf(emptyList<PricebookItem>())

    GlobalScope.launch {
        val response = PricebookManager().getPricebook(1, 20)
        pricebookState.value = response.data
    }

    MaterialTheme {
        Column {
            Text(text = PricebookManager().providePlatform(), modifier = Modifier.padding(8.dp).fillMaxWidth())
            LazyColumnFor(items = pricebookState.value, modifier = Modifier.padding(12.dp)) {
                ItemCard(it)
            }
        }
    }
}

@Composable
private fun ItemCard(pricebookItem: PricebookItem) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.preferredHeight(50.dp).preferredWidth(50.dp)) {
                Image(
                    generateImage(url = pricebookItem.thumbnailUrl).value,
                    modifier = Modifier.fillMaxHeight().fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier.fillMaxHeight().padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(pricebookItem.name.orEmpty())
                Text("$" + pricebookItem.price.toString())
            }
        }
    }
}
