package tmidev.marvelheroes.framework.remote.response

import com.google.gson.annotations.SerializedName
import tmidev.core.domain.model.Character

data class CharacterResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
) {
    fun toCharacterModel() = Character(
        name = name,
        imageUrl = "${thumbnail.path}.${thumbnail.extension}".replace(
            oldValue = "http",
            newValue = "https"
        )
    )
}