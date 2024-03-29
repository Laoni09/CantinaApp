package com.example.cantinaapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        //não tá atualizando na main (ver vídeo do bruno santos)
        binding.buttonEditarCategoria.setOnClickListener {
            val name = binding.editNomeCategoria.text.toString().trim()
            if(name.isNotEmpty()) {
                db.categoriaUpdate(categoriaId, name)
                setResult(1, i)
                finish()
            }else if(name.isEmpty()){
                Toast.makeText(applicationContext, "CATEGORIA SEM NOME!", Toast.LENGTH_LONG).show()
            }
        }

        binding.buttonCancelarCategoria.setOnClickListener {
            finish()
        }

        binding.buttonExcluirCategoria.setOnClickListener {
            db.categoriaDelete(categoriaId)
            setResult(1, i)
            finish()
        }

    }
}