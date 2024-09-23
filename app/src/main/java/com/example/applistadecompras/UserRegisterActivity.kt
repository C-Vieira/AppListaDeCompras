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
            if(!binding.nameTxtField.text.isNullOrEmpty()){
                if(!binding.emailTxtField.text.isNullOrEmpty()){
                    if(!binding.passwordTxtField.text.isNullOrEmpty()){
                        if(!binding.confirmPasswordTxtField.text.isNullOrEmpty()){
                            if(binding.passwordTxtField.text.toString() == binding.confirmPasswordTxtField.text.toString()){
                                val name: String = binding.nameTxtField.text.toString()
                                val email: String = binding.emailTxtField.text.toString()
                                val password: String = binding.passwordTxtField.text.toString()

                                UserDataBase.addUser(User(name, email, password))

                                Snackbar.make(findViewById(android.R.id.content), "Usuário Cadastrado com Sucesso!", Snackbar.LENGTH_LONG).show()
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
}