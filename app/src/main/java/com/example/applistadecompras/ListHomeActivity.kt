package com.example.applistadecompras

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applistadecompras.databinding.ListHomeViewBinding
import com.google.android.material.snackbar.Snackbar

class ListHomeActivity: Activity() {
    private lateinit var binding: ListHomeViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ListHomeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val testList = listOf(
            ShoppingList("Teste1", "https://www.gstatic.com/webp/gallery/1.jpg"),
            ShoppingList("Teste2", "https://www.gstatic.com/webp/gallery/2.jpg"),
            ShoppingList("Teste3", null),
            ShoppingList("Teste4", null),
            ShoppingList("Teste5", null),
            ShoppingList("Teste6", null),
            ShoppingList("Teste7", null),
            ShoppingList("Teste8", null),
            ShoppingList("Teste9", null),
            ShoppingList("Teste10", null),
            ShoppingList("Teste11", null),
            ShoppingList("Teste12", null)
        )

        val adapter = ShoppingListAdapter(testList, ::onListItemClicked)
        val layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

        binding.shoppingListRecylerview.adapter = adapter
        binding.shoppingListRecylerview.layoutManager = layoutManager

        binding.logoutButton.setOnClickListener{
            //TODO Pop back to login view
        }

        binding.addListButton.setOnClickListener{
            // Invoke ListAddActivity
            Intent(applicationContext, ListAddActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun onListItemClicked(list: ShoppingList){
        // Invoke ListItemsActivity
        Intent(applicationContext, ListItemsActivity::class.java).also {
            startActivity(it)
        }
    }
}