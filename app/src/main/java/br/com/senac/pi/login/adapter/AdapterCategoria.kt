package br.com.senac.pi.login.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.senac.pi.R
import br.com.senac.pi.login.model.Produto
import br.com.senac.pi.login.model.ProdutosCategoria

class AdapterCategoria(
    private val context: Context,
    private val categoriasProutos: ArrayList<ProdutosCategoria>
) :
    RecyclerView.Adapter<AdapterCategoria.CategoriaViewHolder>() {

    val listOfProducts: List<Produto> = listOf<Produto>()

    inner class CategoriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id_categoria = itemView.findViewById<TextView>(R.id.categoriaProd)
        val recyclerViewProdutos = itemView.findViewById<RecyclerView>(R.id.rv_produtos)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriaViewHolder {
        val itemLista = LayoutInflater.from(context).inflate(R.layout.categoria_item, parent, false)
        val holder = CategoriaViewHolder(itemLista)
        return holder
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.id_categoria.text = categoriasProutos[position].categoria.nm_categoria
        holder.recyclerViewProdutos.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.recyclerViewProdutos.setHasFixedSize(true)

        val produtosFiltradosPorCategoriaOnly = ArrayList<Produto>()
            categoriasProutos[position].produtos?.forEach{ produto ->
            if(produto.id_categoria == categoriasProutos[position].categoria.id){
                produtosFiltradosPorCategoriaOnly.add(produto)
            }
        }
        //Configurando o Adapter
        holder.recyclerViewProdutos.adapter = AdapterProduto(context, produtosFiltradosPorCategoriaOnly)
    }

    override fun getItemCount(): Int = categoriasProutos.size
}