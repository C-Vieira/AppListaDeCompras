package com.example.applistadecompras

class ShoppingList (
    var title: String,
    var imageUrl: Any?,
    var listItems: MutableList<ShoppingListItem> = mutableListOf()
)