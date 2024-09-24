package com.example.applistadecompras

import android.app.Activity
import android.os.Bundle
import com.example.applistadecompras.databinding.ListHomeViewBinding

class ListHomeActivity: Activity() {
    private lateinit var binding: ListHomeViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ListHomeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testTextView.text = "Hello World!"
    }
}