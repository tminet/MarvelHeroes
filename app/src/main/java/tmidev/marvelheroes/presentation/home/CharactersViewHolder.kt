package tmidev.marvelheroes.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tmidev.core.domain.model.Character
import tmidev.marvelheroes.databinding.ItemCharacterBinding
import tmidev.marvelheroes.framework.imageloader.ImageLoader
import tmidev.marvelheroes.util.OnCharacterItemClick

class CharactersViewHolder(
    private val imageLoader: ImageLoader,
    private val itemCharacterBinding: ItemCharacterBinding,
    private val onItemClick: OnCharacterItemClick
) : RecyclerView.ViewHolder(itemCharacterBinding.root) {
    fun bind(character: Character) {
        itemCharacterBinding.apply {
            imageLoader.load(
                imageViewCharacter,
                character.imageUrl
            )

            textViewCharacterName.text = character.name
            imageViewCharacter.transitionName = character.name

            root.setOnClickListener {
                onItemClick.invoke(character, imageViewCharacter)
            }
        }
    }

    companion object {
        fun create(
            imageLoader: ImageLoader,
            parent: ViewGroup,
            onItemClick: OnCharacterItemClick
        ): CharactersViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemCharacterBinding.inflate(inflater, parent, false)
            return CharactersViewHolder(imageLoader, itemBinding, onItemClick)
        }
    }
}