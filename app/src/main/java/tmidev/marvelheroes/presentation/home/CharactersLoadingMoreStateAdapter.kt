package tmidev.marvelheroes.presentation.home

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class CharactersLoadingMoreStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<CharactersLoadingMoreStateViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = CharactersLoadingMoreStateViewHolder.create(parent = parent, retry = retry)

    override fun onBindViewHolder(
        holder: CharactersLoadingMoreStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState = loadState)
}