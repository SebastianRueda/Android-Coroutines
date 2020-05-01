package com.infinixsoft.coroutines

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class ApiClient {
    companion object {
        val baseUrl = "https://pokeapi.co/api/v2/"

        val service = createService()

        fun createService(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }

        interface ApiService {
            @GET("pokemon")
            suspend fun getPokemon(@Query("offset") offset: Int, @Query("limit") limit: Int): PokemonRequest
        }
    }
}