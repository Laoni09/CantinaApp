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
import com.example.cantinaapp.adapter.CategoriaListAdapter
import com.example.cantinaapp.data.DBHelper
import com.example.cantinaapp.model.Categoria
import com.example.cantinaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var result: ActivityResultLauncher<Intent>
    private lateinit var listaCategorias: ArrayList<Categoria>
    private lateinit var db: DBHelper
    private var pos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DBHelper(applicationContext)

        loadList()

        binding.recyclerViewCategoria.layoutManager = LinearLayoutManager(this)

        binding.buttonAdd.setOnClickListener {
            result.launch(Intent(applicationContext, AdicionarCategoria::class.java))
        }
        //trata o valor de retorno da activity anterior
        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.data != null && it.resultCode == 1){
                loadList()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadList() {
        listaCategorias = db.categoriaListSelectAll()
        //tratar a CategoriaActivity
        val adapter = CategoriaListAdapter(listaCategorias, CategoriaListAdapter.OnClickListener { categoria ->
            val i = Intent(applicationContext, CategoriaActivity::class.java)
            i.putExtra("categoriaId", categoria.id)
            result.launch(i)
        }, CategoriaListAdapter.OnLongClickListener { categoria ->
            val i = Intent(applicationContext, EditarCategoriaActivity::class.java)
            i.putExtra("name", categoria.name)
            i.putExtra("id", categoria.id)
            result.launch(i)
        })

        binding.recyclerViewCategoria.adapter = adapter

        adapter.notifyDataSetChanged()
    }

}