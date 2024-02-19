package com.example.cantinaapp.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cantinaapp.R
import com.example.cantinaapp.data.DBHelper
import com.example.cantinaapp.databinding.ActivityEditarProdutoBinding
import com.example.cantinaapp.model.Produto

class EditarProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarProdutoBinding
    private lateinit var result: ActivityResultLauncher<Intent>
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent
        val db = DBHelper(applicationContext)

        val produtoId = i.extras!!.getInt("id")
        /*val name = i.extras!!.getString("name").toString()
        val price = i.extras!!.getFloat("price")
        val imageId = i.extras!!.getInt("imageId")*/
        val categoriaId = i.extras!!.getInt("categoriaId")
        //fazer a conexão pelo intent e não pelo db pra ficar mais rápido
        val produto = db.selectProdutoById(produtoId, categoriaId)
        var imageId = produto.imageId

        binding.imageEditarImagem.setImageResource(imageId)
        binding.editNomeProduto.setText(produto.name)
        binding.editPrecoProduto.setText(produto.price.toString())


        binding.imageEditarImagem.setOnClickListener {
            val j = Intent(applicationContext, ProductImageSelectionActivity::class.java)
            result.launch(j)
        }

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.data != null && it.resultCode == 1) {
                imageId = it.data?.extras!!.getInt("id")
                binding.imageEditarImagem.setImageDrawable(resources.getDrawable(imageId))
            } else {
                imageId = -1
                binding.imageEditarImagem.setImageResource(R.drawable.baseline_add_photo_alternate_24)
            }
        }

        binding.buttonEditarProduto.setOnClickListener {
            val newName = binding.editNomeProduto.text.toString()
            val newPrice = binding.editPrecoProduto.text.toString().toFloat()
            val newImageId = imageId
            db.produtoUpdate(produtoId, newName, newPrice, newImageId, categoriaId)
            setResult(1, i)
            finish()
        }

        binding.buttonCancelarProduto.setOnClickListener {
            finish()
        }

        binding.buttonExcluirProduto.setOnClickListener {
            db.produtoDelete(produtoId)
            finish()
        }
    }
}