package com.example.cantinaapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cantinaapp.R
import com.example.cantinaapp.data.DBHelper
import com.example.cantinaapp.databinding.ActivityEditarCategoriaBinding

class EditarCategoriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarCategoriaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarCategoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent
        val db = DBHelper(applicationContext)

        binding.editNomeCategoria.setText(i.extras?.getString("name"))
        val categoriaId = i.extras!!.getInt("id")

        //tá dando erro em algum lugar (talvez)
        binding.editNomeCategoria.setOnClickListener {
            val name = binding.editNomeCategoria.text.toString().trim()
            if(!name.isEmpty()) {
                db.categoriaUpdate(categoriaId, name)
            }
            finish()
        }

        binding.buttonCancelarCategoria.setOnClickListener {
            finish()
        }

    }
}