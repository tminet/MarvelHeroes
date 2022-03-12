package tmidev.marvelheroes.presentation.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailViewArg(
    val characterId: Int,
    val name: String,
    val imageUrl: String
) : Parcelable