package com.example.cantinaapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cantinaapp.data.DBHelper
import com.example.cantinaapp.databinding.ActivityAdicionarCategoriaBinding

class AdicionarCategoria : AppCompatActivity() {

    private lateinit var binding: ActivityAdicionarCategoriaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdicionarCategoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(this)

        binding.buttonAddCategoria.setOnClickListener {
            val nome = binding.editNomeCategoria.text.toString().trim()
            if(!nome.isEmpty()){
                db.categoriaInsert(nome)
                val i = Intent(this, MainActivity::class.java)
                i.putExtra("name", nome)
                setResult(1, i)
            }
            finish()
        }
    }
}