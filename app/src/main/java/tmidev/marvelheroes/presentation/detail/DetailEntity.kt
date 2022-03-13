package tmidev.marvelheroes.presentation.detail

import androidx.annotation.StringRes

data class DetailChildVE(
    val id: Int,
    val imageUrl: String
)

data class DetailParentVE(
    @StringRes val categoryStringRes: Int,
    val detailChildList: List<DetailChildVE> = listOf()
)