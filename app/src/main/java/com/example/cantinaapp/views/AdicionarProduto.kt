package com.example.cantinaapp.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cantinaapp.R
import com.example.cantinaapp.data.DBHelper
import com.example.cantinaapp.databinding.ActivityAdicionarProdutoBinding

class AdicionarProduto : AppCompatActivity() {

    private lateinit var binding: ActivityAdicionarProdutoBinding
    private lateinit var result: ActivityResultLauncher<Intent>
    private var id: Int? = -1
    //não pode declarar o i = intent global (por quê?)

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdicionarProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent

        val db = DBHelper(applicationContext)

        binding.buttonCancelarProduto.setOnClickListener {
            setResult(0, i)
            finish()
        }

        binding.imageAdicionarImagem.setOnClickListener {
            result.launch(Intent(applicationContext, ProductImageSelectionActivity::class.java))
        }

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.data != null && it.resultCode == 1) {
                id = it.data?.extras?.getInt("id")
                binding.imageAdicionarImagem.setImageDrawable(resources.getDrawable(id!!))
            } else {
                id = -1
                binding.imageAdicionarImagem.setImageResource(R.drawable.baseline_add_photo_alternate_24)
            }
        }

        binding.buttonAddProduto.setOnClickListener {
            val name = binding.editNomeProduto.text.toString().trim()
            val price = binding.editPrecoProduto.text.toString().toFloat()
            var imageId = -1
            if (id != null) {
                imageId = id as Int
            }
            val categoriaId = i.extras!!.getInt("categoriaId")

            if(!name.isEmpty() && !price.equals(0.00F)){
                db.produtoInsert(name, price, imageId, categoriaId)
                //revisar essa parte e ver se realmente precisa
                i.putExtra("name", name)
                i.putExtra("price", price)
                i.putExtra("imageId", imageId)
                setResult(1, i)
            }
            finish()
        }
    }
}