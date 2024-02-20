package com.example.cantinaapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
            val name = binding.editNomeCategoria.text.toString().trim()
            if(name.isNotEmpty()){
                db.categoriaInsert(name)
                val i = Intent(this, MainActivity::class.java)
                i.putExtra("name", name)
                setResult(1, i)
                finish()
            }else if(name.isEmpty()){
                Toast.makeText(applicationContext, "CATEGORIA SEM NOME!", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonCancelarCategoria.setOnClickListener {
            finish()
        }
    }
}