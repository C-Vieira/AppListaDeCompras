package com.example.applistadecompras

data class User(val userName: String, val userEmail: String, val userPassword: String)

object UserDataBase{
    private val users = mutableListOf(User("admin", "admin", "admin"))

    fun addUser(newUser: User){
        users.add(newUser)
    }

    fun findUserByEmail(email: String): User? {
        return users.find { it.userEmail == email }
    }
}