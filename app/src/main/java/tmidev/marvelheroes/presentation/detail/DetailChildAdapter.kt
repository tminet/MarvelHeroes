package tmidev.marvelheroes.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tmidev.marvelheroes.databinding.ItemChildDetailBinding
import tmidev.marvelheroes.framework.imageloader.ImageLoader

class DetailChildAdapter(
    private val detailChildList: List<DetailChildVE>,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<DetailChildAdapter.DetailChildViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailChildViewHolder =
        DetailChildViewHolder.create(parent, imageLoader)

    override fun onBindViewHolder(holder: DetailChildViewHolder, position: Int) {
        holder.bind(detailChildList[position])
    }

    override fun getItemCount(): Int = detailChildList.size

    class DetailChildViewHolder(
        private val itemBinding: ItemChildDetailBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(detailChildVE: DetailChildVE) = itemBinding.apply {
            imageLoader.load(
                imageView = imageViewItemCategory,
                imageUrl = detailChildVE.imageUrl
            )
        }

        companion object {
            fun create(
                parent: ViewGroup,
                imageLoader: ImageLoader
            ): DetailChildViewHolder {
                val itemBinding = ItemChildDetailBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return DetailChildViewHolder(itemBinding, imageLoader)
            }
        }
    }
}