package com.example.applistadecompras

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.applistadecompras.databinding.ShoppingListItemPreviewBinding

class ShoppingListItemAdapter (
    private val items: List<ShoppingListItem>,
    private val onClick: (ShoppingListItem) -> Unit
) : RecyclerView.Adapter<ShoppingListItemAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ShoppingListItemPreviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentItem: ShoppingListItem? = null

        init {
            itemView.setOnClickListener {
                currentItem?.let {
                    onClick(it)
                }
            }
        }

        fun bind(item: ShoppingListItem) {
            currentItem = item

            binding.listItemName.text = item.name
            binding.listItemAmount.text = item.amount.toString() + " " + item.unit

            Glide.with(binding.root.context)
                .load(item.iconUrl)
                .error(ColorDrawable(Color.BLACK))
                .placeholder(ColorDrawable(Color.LTGRAY))
                .centerCrop()
                .into(binding.listItemImageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShoppingListItemPreviewBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}