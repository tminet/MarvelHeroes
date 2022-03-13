package tmidev.marvelheroes.framework.remote.response

import com.google.gson.annotations.SerializedName
import tmidev.core.domain.model.Character

data class CharacterResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
) {
    fun toCharacterModel() = Character(
        id = id,
        name = name,
        imageUrl = thumbnail.getHttpsUrl()
    )
}