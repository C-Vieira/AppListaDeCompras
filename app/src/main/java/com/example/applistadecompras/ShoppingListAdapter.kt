package com.example.applistadecompras

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filter.FilterResults
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.applistadecompras.databinding.ShoppingListPreviewBinding

class ShoppingListAdapter (
    private val items: List<ShoppingList>,
    private val onClick: (ShoppingList) -> Unit
) : RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>(), Filterable {

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
                .error( item.imageUrl ?: "https://i0.wp.com/espaferro.com.br/wp-content/uploads/2024/06/placeholder-103.png?ssl=1")
                .placeholder(ColorDrawable(Color.LTGRAY))
                .centerCrop()
                .into(binding.listImageView)
        }

    }

    private var shoppingListFiltered = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShoppingListPreviewBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = shoppingListFiltered[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = shoppingListFiltered.size

    fun setNewData (data: List<ShoppingList>?){
        shoppingListFiltered = data.orEmpty()
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return shoppingListFilter
    }

    private val shoppingListFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val text = constraint.toString().orEmpty()

            val resultList = ArrayList<ShoppingList>()

            if(text.isEmpty()){
                resultList.addAll(items)
            }else{
                items.filter { it.title.lowercase().contains(text.lowercase()) }
                    .forEach { list ->
                        resultList.add(list)
                    }
            }

            return FilterResults().apply {
                values = resultList
                count = resultList.size
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            val result = if(results?.values == null){
                ArrayList()
            }else{
                results.values as ArrayList<ShoppingList>
            }

            setNewData(result)
        }
    }

}