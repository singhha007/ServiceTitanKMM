package com.servicetitan.kmm.stAndroid

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.servicetitan.kmm.shared.ShowManager
import com.servicetitan.kmm.shared.model.Show
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

@Composable
@Preview
fun MainContentView() {
    val dataList = mutableStateOf(emptyList<Show>())
    GlobalScope.launch { dataList.value = ShowManager().getPopularShows() }
    MaterialTheme {
        Column {
            Text(text = ShowManager().provideBuild(), modifier = Modifier.padding(8.dp).fillMaxWidth(), textAlign = TextAlign.Center)
            Text(text = ShowManager().provideCurrentDate(), modifier = Modifier.padding(8.dp).fillMaxWidth(), textAlign = TextAlign.Center)
            LazyColumnFor(items = dataList.value, modifier = Modifier.padding(12.dp)) {
                ItemCard(it)
            }
        }
    }
}

@Composable
private fun ItemCard(show: Show) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(4.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.preferredHeight(100.dp).preferredWidth(100.dp)) {
                Image(
                    generateImage(url = IMAGE_URL + show.backdropPath).value,
                    modifier = Modifier.fillMaxHeight().fillMaxWidth()
                )
            }

            Column(modifier = Modifier.fillMaxHeight().padding(start = 12.dp)) {
                Text(show.name)
                Text(show.overview.orEmpty())
            }
        }
    }
}