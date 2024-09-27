package com.example.applistadecompras

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.applistadecompras.databinding.ListItemAddViewBinding
import com.google.android.material.snackbar.Snackbar

class ListItemAddActivity: Activity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ListItemAddViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ListItemAddViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val unitSpinner: Spinner = binding.itemUnitSpinner
        unitSpinner.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            this,
            R.array.list_item_unit_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            unitSpinner.adapter = adapter
        }

        val categorySpinner: Spinner = binding.itemCategorySpinner
        categorySpinner.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            this,
            R.array.list_item_category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            categorySpinner.adapter = adapter
        }

        binding.itemAddButton.setOnClickListener{
            val name = binding.itemTitleTxtField.text.toString()
            val icon = null
            val amount = binding.itemAmountTxtField.text.toString().toInt()
            val unit = binding.itemUnitSpinner.selectedItem.toString()
            val category = binding.itemCategorySpinner.selectedItem.toString()

            UserDataBase.currentList.listItems.add(ShoppingListItem(name, icon, amount, unit, category))

            Snackbar.make(findViewById(android.R.id.content), "Item Criado com Sucesso!", Snackbar.LENGTH_SHORT).show()
        }
    }

    // NOTE: This is called immediately when the activity is invoked
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val item = parent.getItemAtPosition(pos)
        Snackbar.make(findViewById(android.R.id.content), "Item Selected: $item", Snackbar.LENGTH_LONG).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Snackbar.make(findViewById(android.R.id.content), "Nothing Selected", Snackbar.LENGTH_LONG).show()
    }
}