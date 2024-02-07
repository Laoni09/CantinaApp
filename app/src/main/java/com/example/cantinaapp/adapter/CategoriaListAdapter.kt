package com.example.cantinaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cantinaapp.R
import com.example.cantinaapp.model.Categoria
import kotlinx.coroutines.NonDisposableHandle.parent

class CategoriaListAdapter(val listaCategorias: ArrayList<Categoria>, val onClickListener: OnClickListener) :
    RecyclerView.Adapter<CategoriaListAdapter.CategoriaViewHolder>() {

    class CategoriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.row_categoria)
    }

    class OnClickListener(val clickListener: (categoria: Categoria) -> Unit) {
        fun onClick(categoria: Categoria) = clickListener(categoria)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_categoria_list, parent, false)

        return CategoriaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaCategorias.size
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = listaCategorias[position]
        holder.textView.setText(categoria.name)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(categoria)
        }
    }
}