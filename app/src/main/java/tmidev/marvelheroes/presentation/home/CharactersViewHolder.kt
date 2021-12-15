package tmidev.marvelheroes.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tmidev.core.domain.model.Character
import tmidev.marvelheroes.R
import tmidev.marvelheroes.databinding.ItemCharacterBinding

class CharactersViewHolder(
    private val itemCharacterBinding: ItemCharacterBinding
) : RecyclerView.ViewHolder(itemCharacterBinding.root) {
    fun bind(character: Character) {
        itemCharacterBinding.apply {
            Glide.with(itemView)
                .load(character.imageUrl)
                .placeholder(R.drawable.ic_image_error)
                .fallback(R.drawable.ic_image_error)
                .into(imageViewCharacter)

            textViewCharacterName.text = character.name
        }
    }

    companion object {
        fun create(parent: ViewGroup): CharactersViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemCharacterBinding.inflate(inflater, parent, false)
            return CharactersViewHolder(itemBinding)
        }
    }
}