package com.example.cantinaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cantinaapp.R
import com.example.cantinaapp.model.Produto

class PitchListAdapter(val listaProdutos: ArrayList<Produto>) :
    RecyclerView.Adapter<PitchListAdapter.PitchViewHolder>() {
    var total: Int = 0
    class PitchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageSum: ImageView = itemView.findViewById(R.id.image_sum)
        val imageProduto: ImageView = itemView.findViewById(R.id.image_produto)
        val imageSub: ImageView = itemView.findViewById(R.id.image_sub)
        val textNameProduto: TextView = itemView.findViewById(R.id.text_name_produto)
        val textPrice: TextView = itemView.findViewById(R.id.text_price)
        val editQuant: TextView = itemView.findViewById(R.id.edit_quant)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PitchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_pitch_list, parent, false)

        return PitchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaProdutos.size
    }

    override fun onBindViewHolder(holder: PitchViewHolder, position: Int) {
        val produto = listaProdutos[position]
        holder.textNameProduto.setText(produto.name)
        holder.imageSum.setOnClickListener {
            var price = holder.textPrice.text.toString().toFloat()
            var quant = holder.editQuant.text.toString().toInt()
            price += produto.price
            quant += 1
            holder.textPrice.setText(price.toString())
            holder.editQuant.setText(quant.toString())
        }
        holder.imageSub.setOnClickListener {
            var price = holder.textPrice.text.toString().toFloat()
            var quant = holder.editQuant.text.toString().toInt()
            if(price > 0 && price - produto.price >= 0) {
                price -= produto.price
                quant -= 1
                holder.textPrice.setText(price.toString())
                holder.editQuant.setText(quant.toString())
            }
        }
        if (produto.imageId > 0) {
            holder.imageProduto.setImageResource(produto.imageId)
        } else {
            holder.imageProduto.setImageResource(R.drawable.baseline_add_photo_alternate_24)
        }
    }

    /*fun getTotal(): Float {
        var total = 0
        var i = 0
        var produto: Produto
        do {
            produto = listaProdutos[i]
            produto.price
        } while (i < itemCount)
    }*/
}