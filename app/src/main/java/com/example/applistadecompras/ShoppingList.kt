package com.example.applistadecompras

class ShoppingList (
    val title: String,
    val imageUrl: String?,
    val listItems: MutableList<ShoppingListItem> = mutableListOf()
)