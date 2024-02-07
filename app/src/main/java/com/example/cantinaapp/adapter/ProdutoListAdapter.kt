package com.example.cantinaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cantinaapp.R
import com.example.cantinaapp.model.Produto

class ProdutoListAdapter(val listaProdutos: ArrayList<Produto>)
    : RecyclerView.Adapter<ProdutoListAdapter.ProdutoViewHolder>() {

    class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textNomeProduto: TextView = itemView.findViewById(R.id.row_nome_produto)
        val textPrecoProduto: TextView = itemView.findViewById(R.id.row_preco_produto)
        val imageMiniProduto: ImageView = itemView.findViewById(R.id.image_mini_produto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_produto_list, parent, false)

        return ProdutoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaProdutos.size
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = listaProdutos[position]
        holder.textNomeProduto.setText(produto.name)
        holder.textPrecoProduto.setText(produto.price.toString())
        if (produto.imageId > 0) {
            holder.imageMiniProduto.setImageResource(produto.imageId)
        } else {
            holder.imageMiniProduto.setImageResource(R.drawable.baseline_add_photo_alternate_24)
        }
    }
}