package com.example.applistadecompras

class ShoppingListItem (
    var name: String,
    var icon: Int?,
    var amount: Int,
    var unit: String,
    var category: String,
    var isSelected: Boolean = false
)