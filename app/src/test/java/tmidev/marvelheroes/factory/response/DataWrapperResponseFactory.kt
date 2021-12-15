package tmidev.marvelheroes.factory.response

import tmidev.marvelheroes.framework.remote.response.CharacterResponse
import tmidev.marvelheroes.framework.remote.response.DataContainerResponse
import tmidev.marvelheroes.framework.remote.response.DataWrapperResponse
import tmidev.marvelheroes.framework.remote.response.ThumbnailResponse

class DataWrapperResponseFactory {
    fun create() = DataWrapperResponse(
        copyright = "Copyright",
        data = DataContainerResponse(
            offset = 0,
            total = 2,
            results = listOf(
                CharacterResponse(
                    id = "01",
                    name = "Hero 1",
                    thumbnail = ThumbnailResponse(
                        path = "url_hero_1",
                        extension = "jpg"
                    )
                ),
                CharacterResponse(
                    id = "02",
                    name = "Hero 2",
                    thumbnail = ThumbnailResponse(
                        path = "url_hero_2",
                        extension = "jpg"
                    )
                )
            )
        )
    )
}