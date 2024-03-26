package br.com.fiap.rickandmorty.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitFactory {

  private val URL_BASE = "https://rickandmortyapi.com/api/"

  private val retrofitFactory = Retrofit
    .Builder()
    .baseUrl(URL_BASE)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  fun listarTodosOsPersonagens(): PersonagemService{
    return retrofitFactory.create(PersonagemService::class.java)
  }

  fun buscarPersonagemPeloId(): PersonagemService{
    return retrofitFactory.create(PersonagemService::class.java)
  }

}