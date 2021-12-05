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
import br.com.senac.pi.login.servicos.urlImage
import com.squareup.picasso.Picasso

class AdapterProduto(private val context: Context, private val produtos: List<Produto>?) :
    RecyclerView.Adapter<AdapterProduto.ProdutoViewHolder>() {

    inner class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img_produto = itemView.findViewById<ImageView>(R.id.imgProduto)
        val nm_produto = itemView.findViewById<TextView>(R.id.nomeProduto)
        val vl_produto = itemView.findViewById<TextView>(R.id.precoProduto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val itemLista = LayoutInflater.from(context).inflate(R.layout.produto_item, parent, false)
        val holder = ProdutoViewHolder(itemLista)
        return holder
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.nm_produto.text = produtos?.get(position)?.nm_produto ?: ""
        holder.vl_produto.text = "R$ ${produtos?.get(position)?.vl_produto.toString()}"

        Picasso.get().load(urlImage + produtos?.get(position)?.img_produto)
            .error(R.drawable.ic_launcher_background)
            .into(holder.img_produto)
    }

    override fun getItemCount(): Int = produtos!!.size

}