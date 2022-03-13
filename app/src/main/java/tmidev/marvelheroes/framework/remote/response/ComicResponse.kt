package tmidev.marvelheroes.framework.remote.response

import com.google.gson.annotations.SerializedName
import tmidev.core.domain.model.Comic

data class ComicResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
) {
    fun toComicModel() = Comic(
        id = id,
        imageUrl = thumbnail.getHttpsUrl()
    )
}