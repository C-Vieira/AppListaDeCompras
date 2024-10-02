package com.example.applistadecompras

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.graphics.drawable.toDrawable
import com.example.applistadecompras.databinding.ListItemAddViewBinding
import com.google.android.material.snackbar.Snackbar

class ListItemAddActivity: Activity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ListItemAddViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ListItemAddViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editMode: Boolean = intent.getBooleanExtra("EDIT_MODE", false)

        if(editMode){
            binding.listItemAddHeader.text = "Editar Item"
            binding.itemAddButton.text = "Editar"

            // Fill in with current values
            binding.itemTitleTxtField.hint = UserDataBase.currentListItem.name
            binding.itemAmountTxtField.hint = UserDataBase.currentListItem.amount.toString()

            // Show delete button
            binding.deleteListItemButton.visibility = View.VISIBLE
        }

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

        binding.deleteListItemButton.setOnClickListener {
            UserDataBase.currentList.listItems.remove(UserDataBase.currentListItem)

            Snackbar.make(findViewById(android.R.id.content), "Item Deletado com Sucesso!", Snackbar.LENGTH_SHORT).show()
            finish()
        }

        binding.itemAddButton.setOnClickListener{
            val name = binding.itemTitleTxtField.text.toString()
            val amount = binding.itemAmountTxtField.text.toString()
            val unit = binding.itemUnitSpinner.selectedItem.toString()
            val category = binding.itemCategorySpinner.selectedItem.toString()
            var icon: Int? = null

            when (category) {
                "fruta" -> icon = R.drawable.ic_icecream
                "verdura" -> icon = R.drawable.ic_cookie
                "carne" -> icon = R.drawable.ic_food
            }

            if(name.isNotEmpty() and amount.isNotEmpty()){
                try{
                    if(editMode){
                        val currentItem = UserDataBase.currentListItem

                        currentItem.name = name
                        currentItem.amount = amount.toInt()
                        currentItem.unit = unit
                        currentItem.category = category

                        Snackbar.make(findViewById(android.R.id.content), "Item Editado com Sucesso!", Snackbar.LENGTH_SHORT).show()
                    }else{
                        UserDataBase.currentList.listItems.add(ShoppingListItem(name, icon, amount.toInt(), unit, category))

                        Snackbar.make(findViewById(android.R.id.content), "Item Criado com Sucesso!", Snackbar.LENGTH_SHORT).show()

                        binding.itemTitleTxtField.text.clear()
                        binding.itemAmountTxtField.text.clear()
                    }

                }catch(e: NumberFormatException){
                    Snackbar.make(findViewById(android.R.id.content), "Valor Inválido para Quantidade", Snackbar.LENGTH_SHORT).show()
                    binding.itemAmountTxtField.text.clear()
                }

            }else{
                Snackbar.make(findViewById(android.R.id.content), "Por Favor, Preencha todos os Campos", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    // NOTE: This is called immediately when the activity is invoked
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        //val item = parent.getItemAtPosition(pos)
        //Snackbar.make(findViewById(android.R.id.content), "Item Selected: $item", Snackbar.LENGTH_LONG).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Snackbar.make(findViewById(android.R.id.content), "Nothing Selected", Snackbar.LENGTH_LONG).show()
    }
}