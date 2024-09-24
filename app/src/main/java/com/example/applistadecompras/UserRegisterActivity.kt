package com.example.applistadecompras

import android.app.Activity
import android.os.Bundle
import com.example.applistadecompras.databinding.UserRegisterViewBinding
import com.google.android.material.snackbar.Snackbar

class UserRegisterActivity : Activity() {
    private lateinit var binding: UserRegisterViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UserRegisterViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener{
            val nameTxtFieldString = binding.nameTxtField.text.toString()
            val emailTxtFieldString = binding.emailTxtField.text.toString()
            val passwordTxtFieldString = binding.passwordTxtField.text.toString()
            val confirmPasswordTxtFieldString = binding.confirmPasswordTxtField.text.toString()

            if(nameTxtFieldString.isNotEmpty()){
                if(emailTxtFieldString.isNotEmpty()){
                    if(passwordTxtFieldString.isNotEmpty()){
                        if(confirmPasswordTxtFieldString.isNotEmpty()){

                            // Check if Password equals Confirmed Password
                            if(passwordTxtFieldString == confirmPasswordTxtFieldString){
                                val name: String = nameTxtFieldString
                                val email: String = emailTxtFieldString
                                val password: String = passwordTxtFieldString

                                UserDataBase.addUser(User(name, email, password))

                                Snackbar.make(findViewById(android.R.id.content), "Usuário Cadastrado com Sucesso!", Snackbar.LENGTH_LONG).show()

                                clearTextFields()
                            }else{
                                Snackbar.make(findViewById(android.R.id.content), "Senha e Confirmação Diferentes", Snackbar.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }else{
                Snackbar.make(findViewById(android.R.id.content), "Por Favor, Preencha Todos os Campos", Snackbar.LENGTH_LONG).show()
            }
        }

    }

    private fun clearTextFields(){
        binding.nameTxtField.text.clear()
        binding.emailTxtField.text.clear()
        binding.passwordTxtField.text.clear()
        binding.confirmPasswordTxtField.text.clear()
    }
}