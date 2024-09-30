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

        Snackbar.make(findViewById(android.R.id.content), "Bem Vindo ${UserDataBase.currentUser.userName}", Snackbar.LENGTH_LONG).show()

        //Get listSource from Current User and sort
        val listSource = UserDataBase.currentUser.userLists.sortedBy { it.title[0] }

        val adapter = ShoppingListAdapter(listSource, ::onListItemClicked)
        val layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)

        binding.shoppingListRecylerview.adapter = adapter
        binding.shoppingListRecylerview.layoutManager = layoutManager

        binding.logoutButton.setOnClickListener{
            // Pop back to login screen
            finish()

            // Reset Current User and List
            UserDataBase.currentUser = User("empty", "empty", "empty")
            UserDataBase.currentList = ShoppingList("empty", "empty", mutableListOf())
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
            UserDataBase.currentList = list
            startActivity(it)
        }
    }

    override fun onResume() {
        super.onResume()

        // Update Recycler View Data Source
        val listSource = UserDataBase.currentUser.userLists.sortedBy { it.title[0] }
        binding.shoppingListRecylerview.adapter = ShoppingListAdapter(listSource, ::onListItemClicked)
    }
}