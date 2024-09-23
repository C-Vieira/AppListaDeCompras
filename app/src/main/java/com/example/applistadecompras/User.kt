package com.example.applistadecompras

data class User(val userName: String, val userEmail: String, val userPassword: String)

object UserDataBase{
    private val users = mutableListOf<User>()

    fun addUser(newUser: User){
        users.add(newUser)
    }
}