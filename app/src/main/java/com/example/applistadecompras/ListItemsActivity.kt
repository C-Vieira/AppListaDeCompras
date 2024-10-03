package com.example.applistadecompras

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applistadecompras.databinding.ListItemsViewBinding

class ListItemsActivity: Activity() {
    private lateinit var binding: ListItemsViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ListItemsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get listName
        binding.listItemsHeader.text = UserDataBase.currentList.title

        // Get listItemsSource from Current List and sort
        val listItemsSource = UserDataBase.currentList.listItems
            .sortedBy { it.name[0] }
            .sortedBy { it.category }
            .sortedBy { it.isSelected }

        val adapter = ShoppingListItemAdapter(listItemsSource, ::onListItemClicked)
        val layoutManager = LinearLayoutManager(this)

        binding.shoppingListItemsRecylerview.adapter = adapter
        binding.shoppingListItemsRecylerview.layoutManager = layoutManager

        binding.addListItemButton.setOnClickListener {
            // Invoke ListItemAddActivity
            Intent(applicationContext, ListItemAddActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.listEditButton.setOnClickListener {
            // Invoke ListAddActivity for Editing
            Intent(applicationContext, ListAddActivity::class.java).also {
                it.putExtra("EDIT_MODE", true)
                //startActivity(it)
                startActivityForResult(it, 1)
            }
        }

        val searchView: SearchView = binding.listItemSearchView
        searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val results = filter(newText).sortedBy { it.name[0] }
                binding.shoppingListItemsRecylerview.adapter = ShoppingListItemAdapter(results, ::onListItemClicked)

                return true
            }
        })
    }

    private fun filter(query: String?): List<ShoppingListItem>{
        val filteredList = ArrayList<ShoppingListItem>()

        if (query != null){
            for(i in UserDataBase.currentList.listItems){
                if(i.name.lowercase().contains(query.lowercase())){
                    filteredList.add(i)
                }
            }
        }

        return filteredList
    }

    private fun onListItemClicked(item: ShoppingListItem){
        // Invoke ListItemAddActivity for Editing
        Intent(applicationContext, ListItemAddActivity::class.java).also {
            UserDataBase.currentListItem = item
            it.putExtra("EDIT_MODE", true)
            startActivity(it)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if((requestCode == 1) && UserDataBase.justDeleted){
            UserDataBase.justDeleted = false
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        // Update listName
        binding.listItemsHeader.text = UserDataBase.currentList.title

        // Update Recycler View Data Source
        val listItemsSource = UserDataBase.currentList.listItems
            .sortedBy { it.name[0] }
            .sortedBy { it.category }
            .sortedBy { it.isSelected }
        binding.shoppingListItemsRecylerview.adapter = ShoppingListItemAdapter(listItemsSource, ::onListItemClicked)
    }
}