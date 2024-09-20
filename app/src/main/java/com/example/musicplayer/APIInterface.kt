package com.example.musicplayer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIInterface {
    @GET("search")
    @Headers("x-rapidapi-key: cd92783dd8msh75ec1584169f84ep14c95ejsne9900ed3790a","x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com")
    fun getMusic(@Query("q")query: String):Call<music>

}