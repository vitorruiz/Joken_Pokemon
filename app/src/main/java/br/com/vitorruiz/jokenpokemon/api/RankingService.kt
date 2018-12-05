package br.com.vitorruiz.jokenpokemon.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RankingService {

    @GET("pontuacao")
    fun getPontuacao(): Call<List<UserRankingVO>>

    @POST("pontuacao")
    fun sendPontuacao(@Body userRankingVO: UserRankingVO): Call<Void>
}