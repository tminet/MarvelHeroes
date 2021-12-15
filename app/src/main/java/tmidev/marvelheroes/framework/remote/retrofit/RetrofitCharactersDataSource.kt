package tmidev.marvelheroes.framework.remote.retrofit

import tmidev.core.data.datasource.RemoteCharactersDataSource
import tmidev.marvelheroes.framework.remote.response.DataWrapperResponse
import javax.inject.Inject

class RetrofitCharactersDataSource @Inject constructor(
    private val marvelApi: MarvelApi
) : RemoteCharactersDataSource<DataWrapperResponse> {
    override suspend fun getCharacters(queries: Map<String, String>): DataWrapperResponse {
        return marvelApi.getCharacters(queries = queries)
    }
}