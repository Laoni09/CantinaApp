package com.example.cantinaapp.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cantinaapp.R
import com.example.cantinaapp.adapter.ProdutoListAdapter
import com.example.cantinaapp.data.DBHelper
import com.example.cantinaapp.databinding.ActivityCategoriaBinding
import com.example.cantinaapp.model.Produto

class CategoriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriaBinding
    private lateinit var listaProdutos: ArrayList<Produto>
    private lateinit var result: ActivityResultLauncher<Intent>
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewProduto.layoutManager = LinearLayoutManager(applicationContext)

        val i = intent

        db = DBHelper(applicationContext)

        loadList()

        binding.imageBack.setOnClickListener {
            finish()
        }

        binding.textTitleCategoria.setOnClickListener {
            val categoriaId = i.extras!!.getInt("categoriaId")
            val j = Intent(applicationContext, PitchActivity::class.java)
            j.putExtra("categoriaId", categoriaId)
            result.launch(j)
        }

        binding.buttonAddProduct.setOnClickListener {
            //result.launch(Intent(applicationContext, AdicionarProduto::class.java))
            val categoriaId = i.extras!!.getInt("categoriaId")
            val j = Intent(applicationContext, AdicionarProduto::class.java)
            j.putExtra("categoriaId", categoriaId)
            result.launch(j)
        }
        //trata o valor de retorno da activity anterior
        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.data != null && it.resultCode == 1){
                loadList()
            } else if (it.data != null && it.resultCode == 0) {
                Toast.makeText(applicationContext, "Operação Cancelada", Toast.LENGTH_SHORT).show()
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadList() {
        val i = intent
        //listaProdutos = db.produtoListSelectByCategoriaId(i.extras!!.getInt("id"))
        val categoriaId = i.extras!!.getInt("categoriaId")
        listaProdutos = db.produtoListSelectByCategoriaId(categoriaId)

        val adapter = ProdutoListAdapter(listaProdutos, ProdutoListAdapter.OnClickListener { produto ->
            val j = Intent(this, EditarProdutoActivity::class.java)
            j.putExtra("id", produto.id)
            j.putExtra("name", produto.name)
            j.putExtra("price", produto.price)
            j.putExtra("imageId", produto.imageId)
            j.putExtra("categoriaId", categoriaId)
            result.launch(j)
        })
        binding.recyclerViewProduto.adapter = adapter

        adapter.notifyDataSetChanged()
    }
}