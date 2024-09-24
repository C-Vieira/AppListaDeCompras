package com.example.applistadecompras

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.applistadecompras.databinding.ShoppingListPreviewBinding

class ShoppingListAdapter (
    private val items: List<ShoppingList>,
    private val onClick: (ShoppingList) -> Unit
) : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ShoppingListPreviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentItem: ShoppingList? = null

        init {
            itemView.setOnClickListener {
                currentItem?.let {
                    onClick(it)
                }
            }
        }

        fun bind (item: ShoppingList) {
            currentItem = item

            binding.listTextView.text = item.title
            Glide.with(binding.root.context)
                .load(item.imageUrl)
                .error("https://i0.wp.com/espaferro.com.br/wp-content/uploads/2024/06/placeholder-103.png?ssl=1")
                .placeholder(ColorDrawable(Color.LTGRAY))
                .centerCrop()
                .into(binding.listImageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShoppingListPreviewBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size
}