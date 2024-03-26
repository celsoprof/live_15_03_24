package br.com.fiap.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.rickandmorty.model.ListaDePersonagens
import br.com.fiap.rickandmorty.model.Personagem
import br.com.fiap.rickandmorty.service.RetrofitFactory
import br.com.fiap.rickandmorty.ui.theme.RickAndMortyTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      RickAndMortyTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Tela()
        }
      }
    }
  }
}

@Composable
fun Tela() {

  var listaDePersonagens by remember {
    mutableStateOf(listOf<Personagem>())
  }

  // Fazer uma requisição para a API
  var requisicao = RetrofitFactory().listarTodosOsPersonagens().listarTodosOsPersonagens()

  requisicao.enqueue(object : Callback<ListaDePersonagens>{
    override fun onResponse(
      call: Call<ListaDePersonagens>,
      response: Response<ListaDePersonagens>
    ) {
      listaDePersonagens = response.body()!!.results
      Log.i("FIAP", "onResponse: ${listaDePersonagens}")
    }

    override fun onFailure(call: Call<ListaDePersonagens>, t: Throwable) {
      Log.e("FIAP", "onFailure: ${t.message}", )
    }

  })

  Image(
    painter = painterResource(id = R.drawable.rick_morty_2),
    contentDescription = "",
    contentScale = ContentScale.Crop,
    modifier = Modifier.fillMaxSize()
  )
  Column {
    LazyColumn{
      items(listaDePersonagens){personagem ->
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 16.dp, vertical = 4.dp),
          colors = CardDefaults.cardColors(containerColor = Color(0x999C27B0))
        ) {
          Column{
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.fillMaxHeight()
            ) {
              AsyncImage(model = personagem.image, contentDescription = "")
              Column(
                modifier = Modifier.padding(horizontal = 8.dp)
              ) {
                Text(
                  text = personagem.name,
                  fontSize = 24.sp,
                  color = Color.White,
                  fontWeight = FontWeight.Bold
                )
                Text(
                  text = personagem.species,
                  fontSize = 12.sp,
                  color = Color.White,
                )
              }
            }
          }
        }
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
  RickAndMortyTheme {
    Tela()
  }
}