package com.example.applistadecompras

import java.io.Serializable

class ShoppingListItem (
    var name: String,
    var iconUrl: String?,
    var amount: Int,
    var unit: String,
    var category: String
): Serializable