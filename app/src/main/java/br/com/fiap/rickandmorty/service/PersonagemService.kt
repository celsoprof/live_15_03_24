package br.com.fiap.rickandmorty.service

import br.com.fiap.rickandmorty.model.ListaDePersonagens
import br.com.fiap.rickandmorty.model.Personagem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PersonagemService {

  @GET("character")
  fun listarTodosOsPersonagens(): Call<ListaDePersonagens>

  @GET("character/{id}")
  fun buscarPersonagemPeloId(@Path("id") id: Int): Call<Personagem>

  @POST("/character")
  fun salvarPersonagem(@Body personagem: Personagem)
}