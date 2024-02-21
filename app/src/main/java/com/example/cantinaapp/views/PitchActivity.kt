package com.example.cantinaapp.views

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cantinaapp.R
import com.example.cantinaapp.adapter.PitchListAdapter
import com.example.cantinaapp.data.DBHelper
import com.example.cantinaapp.databinding.ActivityPitchBinding
import com.example.cantinaapp.model.Produto

class PitchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPitchBinding
    private lateinit var listaProdutos: ArrayList<Produto>
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPitchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent
        val db = DBHelper(applicationContext)

        binding.recyclerViewPitch.layoutManager = LinearLayoutManager(this)

        val categoriaId = i.extras!!.getInt("categoriaId")
        listaProdutos = db.produtoListSelectByCategoriaId(categoriaId)

        val adapter = PitchListAdapter(listaProdutos)
        binding.recyclerViewPitch.adapter = adapter

        adapter.notifyDataSetChanged()
    }
}