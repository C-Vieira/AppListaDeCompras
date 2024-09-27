package com.example.applistadecompras

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.applistadecompras.R.*
import com.example.applistadecompras.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Hello World!
        Glide.with(this)
            .load("https://cdn-icons-png.flaticon.com/512/8539/8539402.png")
            .centerCrop()
            .placeholder(ColorDrawable(Color.BLACK))
            .error(ColorDrawable(Color.LTGRAY))
            .into(binding.imageView)

        binding.loginButton.setOnClickListener{
            val emailString = binding.emailTxtField.text.toString()
            val passwordString = binding.passwordTxtField.text.toString()

            // Get User from UserDataBase
            val user = UserDataBase.findUserByEmail(emailString)

            // Check if User Exists
            if(user != null) {
                // Check if Password Matches
                if(passwordString == user.userPassword){

                    UserDataBase.currentUser = user

                    // Invoke ListHomeActivity
                    Intent(applicationContext, ListHomeActivity::class.java).also {
                        startActivity(it)
                    }

                    binding.emailTxtField.text.clear()
                    binding.passwordTxtField.text.clear()
                }else{
                    Snackbar.make(findViewById(android.R.id.content), "Senha Incorreta", Snackbar.LENGTH_LONG).show()
                    binding.passwordTxtField.text.clear()
                }
            }else{
                Snackbar.make(findViewById(android.R.id.content), "Usuário Inexistente", Snackbar.LENGTH_LONG).show()
                binding.emailTxtField.text.clear()
                binding.passwordTxtField.text.clear()
            }
        }

        binding.accountRegisterButton.setOnClickListener{
            // Invoke UserRegisterActivity
            Intent(applicationContext, UserRegisterActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}