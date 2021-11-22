package br.com.senac.pi.login.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.senac.pi.R
import br.com.senac.pi.login.model.Produto
import com.squareup.picasso.Picasso

class adapterProduto(private val context: Context, private val produtos: MutableList<Produto>): RecyclerView.Adapter<adapterProduto.ProdutoViewHolder>() {

    inner class ProdutoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val id_categoria = itemView.findViewById<TextView>(R.id.categoriaProd)
        val img_produto = itemView.findViewById<ImageView>(R.id.imgProduto)
        val nm_produto = itemView.findViewById<TextView>(R.id.nomeProduto)
        val vl_produto = itemView.findViewById<TextView>(R.id.precoProduto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val itemLista = LayoutInflater.from(context).inflate(R.layout.quadros_item,parent,false)
        val holder = ProdutoViewHolder(itemLista)
        return holder
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.id_categoria.text = produtos[position].id_categoria.toString()
        //holder.img_produto.setImageResource(produtos[position].img_produto)
        holder.nm_produto.text = produtos[position].nm_produto
        holder.vl_produto.text = produtos[position].vl_produto.toString()
    }

    override fun getItemCount(): Int = produtos.size

}