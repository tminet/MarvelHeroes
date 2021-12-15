package tmidev.marvelheroes.framework.remote.retrofit

import retrofit2.http.GET
import retrofit2.http.QueryMap
import tmidev.marvelheroes.framework.remote.response.DataWrapperResponse

interface MarvelApi {
    @GET("characters")
    suspend fun getCharacters(
        @QueryMap queries: Map<String, String>
    ): DataWrapperResponse
}