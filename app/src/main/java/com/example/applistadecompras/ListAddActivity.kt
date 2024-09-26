package com.example.applistadecompras

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.applistadecompras.databinding.ListAddViewBinding
import com.google.android.material.snackbar.Snackbar

class ListAddActivity: Activity() {
    private lateinit var binding: ListAddViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ListAddViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val placeholderUrl = "https://i0.wp.com/espaferro.com.br/wp-content/uploads/2024/06/placeholder-103.png?ssl=1"
        val newShoppingList = ShoppingList(" ", null)

        Glide.with(binding.root)
            .load(newShoppingList.imageUrl ?: placeholderUrl)
            .error(placeholderUrl)
            .placeholder(ColorDrawable(Color.LTGRAY))
            .centerCrop()
            .into(binding.listImageView)

        binding.listAddButton.setOnClickListener {
            val listName = binding.listTitleTxtField.text.toString()
            val listImageUrl = null

            UserDataBase.currentUser.userLists.add(ShoppingList(listName, listImageUrl))

            Snackbar.make(findViewById(android.R.id.content), "Nova Lista Criada Com Sucesso!", Snackbar.LENGTH_SHORT).show()
            binding.listTitleTxtField.text.clear()
        }
    }
}