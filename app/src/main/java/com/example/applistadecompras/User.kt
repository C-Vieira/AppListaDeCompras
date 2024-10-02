package com.example.applistadecompras

data class User(
    val userName: String,
    val userEmail: String,
    val userPassword: String,
    val userLists: MutableList<ShoppingList> = mutableListOf())

object UserDataBase{
    private val users = mutableListOf(User("admin", "admin", "admin"))
    var currentUser: User = User("empty", "empty", "empty")
    var currentList: ShoppingList = ShoppingList("empty", "empty", mutableListOf())
    var currentListItem: ShoppingListItem = ShoppingListItem("empty", null, 0, "un", "fruta")

    var justDeleted: Boolean = false

    fun addUser(newUser: User){
        users.add(newUser)
    }

    fun findUserByEmail(email: String): User? {
        return users.find { it.userEmail == email }
    }
}