package tmidev.marvelheroes.presentation.home

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import tmidev.core.domain.model.Character

class CharactersAdapter : PagingDataAdapter<Character, CharactersViewHolder>(CharactersDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharactersViewHolder.create(parent)

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        getItem(position)?.let { character ->
            holder.bind(character)
        }
    }

    class CharactersDiffUtil : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem
    }
}