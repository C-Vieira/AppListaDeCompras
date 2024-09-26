package com.example.applistadecompras

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applistadecompras.databinding.ListItemsViewBinding
import com.google.android.material.snackbar.Snackbar

class ListItemsActivity: Activity() {
    private lateinit var binding: ListItemsViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ListItemsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val testList = listOf(
            ShoppingListItem("Item1", null),
            ShoppingListItem("Item2", null),
            ShoppingListItem("Item3", null),
            ShoppingListItem("Item4", null),
            ShoppingListItem("Item5", null),
            ShoppingListItem("Item6", null),
            ShoppingListItem("Item7", null),
            ShoppingListItem("Item8", null),
            ShoppingListItem("Item9", null),
            ShoppingListItem("Item10", null),
            ShoppingListItem("Item11", null),
            ShoppingListItem("Item12", null)
        )

        val adapter = ShoppingListItemAdapter(testList, ::onListItemClicked)
        val layoutManager = LinearLayoutManager(this)

        binding.shoppingListItemsRecylerview.adapter = adapter
        binding.shoppingListItemsRecylerview.layoutManager = layoutManager

        binding.addListItemButton.setOnClickListener {
            // Invoke ListItemAddActivity
            Intent(applicationContext, ListItemAddActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun onListItemClicked(item: ShoppingListItem){
        Snackbar.make(findViewById(android.R.id.content), "Item: ${item.name}", Snackbar.LENGTH_LONG).show()
    }
}