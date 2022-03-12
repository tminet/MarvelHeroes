package tmidev.marvelheroes.framework.remote.response

import com.google.gson.annotations.SerializedName
import tmidev.core.domain.model.Event

data class EventResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
) {
    fun toEventModel() = Event(
        id = id,
        imageUrl = thumbnail.getHttpsUrl()
    )
}