package tmidev.marvelheroes.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import tmidev.marvelheroes.databinding.ItemCharacterLoadMoreStateBinding

class CharactersLoadingMoreStateViewHolder(
    itemBinding: ItemCharacterLoadMoreStateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {
    private val binding = ItemCharacterLoadMoreStateBinding.bind(itemView)
    private val progressBar = binding.progressBar
    private val textViewRetryMessage = binding.textViewRetryMessage.also {
        it.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
        textViewRetryMessage.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(
            parent: ViewGroup,
            retry: () -> Unit
        ): CharactersLoadingMoreStateViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemCharacterLoadMoreStateBinding.inflate(inflater, parent, false)
            return CharactersLoadingMoreStateViewHolder(itemBinding, retry)
        }
    }
}