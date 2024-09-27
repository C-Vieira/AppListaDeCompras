package com.example.applistadecompras

class ShoppingList (
    val title: String,
    val imageUrl: Any?,
    val listItems: MutableList<ShoppingListItem> = mutableListOf()
)