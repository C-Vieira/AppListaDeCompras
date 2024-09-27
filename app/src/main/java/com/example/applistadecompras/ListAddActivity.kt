package com.example.applistadecompras

import android.Manifest.permission.READ_MEDIA_IMAGES
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.applistadecompras.databinding.ListAddViewBinding
import com.google.android.material.snackbar.Snackbar

class ListAddActivity: AppCompatActivity() {
    private lateinit var binding: ListAddViewBinding

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            onPermissionResult(isGranted)
        }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { image ->
            showImage(image)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ListAddViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showImage()

        binding.listAddButton.setOnClickListener {

            val listImageUrl = binding.listImageView.drawable

            if(binding.listTitleTxtField.text.isNotEmpty()){
                val listName = binding.listTitleTxtField.text.toString()

                UserDataBase.currentUser.userLists.add(ShoppingList(listName, listImageUrl))

                Snackbar.make(findViewById(android.R.id.content), "Nova Lista Criada Com Sucesso!", Snackbar.LENGTH_SHORT).show()
            }else{
                Snackbar.make(findViewById(android.R.id.content), "Nome da Lista Vazio", Snackbar.LENGTH_SHORT).show()
            }

            binding.listTitleTxtField.text.clear()
        }

        binding.pickImageButton.setOnClickListener{
            selectImage()
        }
    }

    private fun selectImage() {
        when {
            // Permission Granted
            ContextCompat.checkSelfPermission(
                this,
                READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED -> {
                getContent.launch("image/*")
            }

            // Permission Denied Once
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                READ_MEDIA_IMAGES
            ) -> {
                showPermissionRequestExplanation(requestAgain = true)
            }

            // Permission was never asked for before
            else -> {
                requestPermissionLauncher.launch(READ_MEDIA_IMAGES)
            }
        }
    }

    private fun onPermissionResult(isGranted: Boolean) {
        if (isGranted) {
            // Permission Granted
            getContent.launch("image/*")
        } else {
            // Explain why this permission is being requested
            showPermissionRequestExplanation(requestAgain = false)
        }
    }

    private fun showPermissionRequestExplanation(requestAgain: Boolean) {
        val snackbar = Snackbar.make(
            findViewById(android.R.id.content),
            "Permissão Necessária para Seleção de Mídia",
            Snackbar.LENGTH_LONG
        )
        if (requestAgain) {
            snackbar.setAction("Permitir") {
                requestPermissionLauncher.launch(READ_MEDIA_IMAGES)
            }
        }
        snackbar.show()
    }

    private fun showImage(image: Any? = null) {
        val placeholderUrl = "https://i0.wp.com/espaferro.com.br/wp-content/uploads/2024/06/placeholder-103.png?ssl=1"

        val newShoppingList = ShoppingList(" ", image)

        Glide.with(binding.root)
            .load(newShoppingList.imageUrl ?: placeholderUrl)
            .error(placeholderUrl)
            .placeholder(ColorDrawable(Color.LTGRAY))
            .centerCrop()
            .into(binding.listImageView)
    }
}