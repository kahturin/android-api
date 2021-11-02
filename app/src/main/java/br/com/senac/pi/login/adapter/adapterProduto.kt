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

class adapterProduto(private val context: Context, private val produtos: MutableList<Produto>): RecyclerView.Adapter<adapterProduto.ProdutoViewHolder>() {

    inner class ProdutoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //val imagem = itemView.findViewById<ImageView>(R.id.imgProduto)
        //val nome = itemView.findViewById<TextView>(R.id.nomeProduto)
        //val preco = itemView.findViewById<TextView>(R.id.precoProduto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val itemLista = LayoutInflater.from(context).inflate(R.layout.quadros_item,parent,false)
        val holder = ProdutoViewHolder(itemLista)
        return holder
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        // holder.imagem.setImageResource(produtos[position].imagem)
        //holder.nome.text = produtos[position].nome
        //holder.preco.text = produtos[position].preco
    }

    override fun getItemCount(): Int = produtos.size

}