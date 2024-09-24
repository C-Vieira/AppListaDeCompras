package com.example.applistadecompras

import android.app.Activity
import android.os.Bundle
import com.example.applistadecompras.databinding.ListAddViewBinding

class ListAddActivity: Activity() {
    private lateinit var binding: ListAddViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ListAddViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}