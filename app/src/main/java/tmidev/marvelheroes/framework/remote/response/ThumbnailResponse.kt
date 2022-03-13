package tmidev.marvelheroes.framework.remote.response

import com.google.gson.annotations.SerializedName

data class ThumbnailResponse(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
) {
    fun getHttpsUrl() = "${path}.${extension}".replace(
        oldValue = "http",
        newValue = "https"
    )
}