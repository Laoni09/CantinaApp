package com.example.cantinaapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cantinaapp.R
import com.example.cantinaapp.databinding.ActivityProductImageSelectionBinding

class ProductImageSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductImageSelectionBinding
    private lateinit var i: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductImageSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        i = intent

        binding.paoImage.setOnClickListener { sendID(R.drawable.pao) }
        binding.bananaImage.setOnClickListener { sendID(R.drawable.banana) }
        binding.morangoImage.setOnClickListener { sendID(R.drawable.morango) }
        binding.toddynhoImage.setOnClickListener { sendID(R.drawable.toddynho) }
        binding.chocolateImage.setOnClickListener { sendID(R.drawable.chocolate) }
        binding.buttonRemoveImage.setOnClickListener { sendID(R.drawable.baseline_add_photo_alternate_24) }

    }

    private fun sendID(id: Int) {
        i.putExtra("id", id)
        setResult(1, i)
        finish()
    }

}