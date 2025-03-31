package com.bastian.rickmortytestapp.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.bastian.rickmortytestapp.domain.model.CharacterModel
import com.bastian.rickmortytestapp.domain.model.LocationModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    navController: NavHostController,
    characterId: Int,
    viewModel: DetailScreenViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(characterId) {
        viewModel.loadChar(characterId)
    }

    when (uiState) {
        is DetailUiState.Loading -> {
            LoadingScreen()
        }

        is DetailUiState.Success -> {
            val (character, location) = (uiState as DetailUiState.Success)
            CharacterDetailContent(character, location, navController)
        }

        is DetailUiState.Error -> {
            val errorMessage = (uiState as DetailUiState.Error).message
            ErrorScreen(errorMessage, navController)
        }
    }


}

@Composable
fun CharacterDetailContent(
    character: CharacterModel,
    location: LocationModel?,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = character.name,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${character.status} • ${character.species} • ${character.gender}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        InfoRow(title = "Origen:", value = character.origin.name)
        InfoRow(title = "Ubicación actual:", value = character.location.name)

        Spacer(modifier = Modifier.height(16.dp))

        location?.let {
            Text(
                text = "Ubicación detallada",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            InfoRow(title = "Nombre:", value = it.name)
            InfoRow(title = "Tipo:", value = it.type)
            InfoRow(title = "Dimensión:", value = it.dimension)
        } ?: Text(
            text = "No se pudo obtener información de la ubicación",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Aparece en ${character.episodes.size} episodios",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}

@Composable
fun InfoRow(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}


@Composable
fun ErrorScreen(errorMessage: String, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text("Volver")
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
