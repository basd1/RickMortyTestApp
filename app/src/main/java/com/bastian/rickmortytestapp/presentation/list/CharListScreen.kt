package com.bastian.rickmortytestapp.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.bastian.rickmortytestapp.domain.model.CharacterModel
import com.bastian.rickmortytestapp.presentation.navigation.Routes
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharListScreen(navController: NavHostController, viewModel: CharListScreenViewModel = koinViewModel()){
    val chars = viewModel.chars.collectAsLazyPagingItems()
    var searchQuery by remember { mutableStateOf("") }


    Column {
        SearchBar(searchQuery) { newQuery ->
            searchQuery = newQuery
        }

        CharList(chars){ id ->
            navController.navigate(Routes.Detail.createRoute(id))
        }
    }


}

@Composable
fun CharList(
    chars: LazyPagingItems<CharacterModel>,
    onClick: (id: Int) -> Unit
) {

    LazyColumn {
        items(chars.itemCount){
            chars[it]?.let { char ->
                CharItem(char){ id ->
                    onClick(id)
                }
            }
        }
    }
}

@Composable
fun SearchBar(searchQuery: String, onSearchQueryChanged: (String) -> Unit) {
    TextField(
        value = searchQuery,
        onValueChange = { onSearchQueryChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = { Text("Buscar personaje...") },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar") },
        singleLine = true
    )
}

@Composable
fun CharItem(char: CharacterModel, onClick: (id: Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(char.id) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            AsyncImage(
                model = char.image,
                contentDescription = char.name,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 8.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(text = char.name)
                Text(text = char.type)
            }
        }
    }
}
