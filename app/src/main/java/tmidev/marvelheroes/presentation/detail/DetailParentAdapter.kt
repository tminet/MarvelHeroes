package tmidev.marvelheroes.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tmidev.marvelheroes.databinding.ItemParentDetailBinding
import tmidev.marvelheroes.framework.imageloader.ImageLoader

class DetailParentAdapter(
    private val detailParentList: List<DetailParentVE>,
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<DetailParentAdapter.DetailParentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailParentViewHolder =
        DetailParentViewHolder.create(parent, imageLoader)

    override fun onBindViewHolder(holder: DetailParentViewHolder, position: Int) {
        holder.bind(detailParentList[position])
    }

    override fun getItemCount(): Int = detailParentList.size

    class DetailParentViewHolder(
        private val itemBinding: ItemParentDetailBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(detailParentVE: DetailParentVE) {
            itemBinding.textViewItemCategory.text =
                itemView.context.getText(detailParentVE.categoryStringRes)

            itemBinding.recyclerViewChildDetail.apply {
                setHasFixedSize(true)
                adapter = DetailChildAdapter(
                    detailParentVE.detailChildList,
                    imageLoader
                )
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                imageLoader: ImageLoader
            ): DetailParentViewHolder {
                val itemBinding = ItemParentDetailBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return DetailParentViewHolder(itemBinding, imageLoader)
            }
        }
    }
}