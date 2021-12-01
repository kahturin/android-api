package br.com.senac.pi.login.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.senac.pi.R
import br.com.senac.pi.login.model.Pedidos


class adapaterPedidos(private val context: Context, private val pedidos: MutableList<Pedidos>): RecyclerView.Adapter<adapaterPedidos.PedidosViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapaterPedidos.PedidosViewHolder {
        val listItem = LayoutInflater.from(context).inflate(R.layout.pedidos_item, parent, false)
        val holder = PedidosViewHolder(listItem)
        return holder
    }

    override fun onBindViewHolder(holder: adapaterPedidos.PedidosViewHolder, position: Int) {
        holder.status.text = pedidos[position].status
        holder.numero.text = pedidos[position].numero
        holder.vlTotal.text = "Total Pedido: "+pedidos[position].vl_total
        //holder.foto.setImageResource(pedidos[position].foto)
//        holder.foto.setOnClickListener {
//            val intent = Intent(applicationContext, TelaProdutoActivity::class.java)
//            startActivity(intent)
//        }
    }

    override fun getItemCount(): Int = pedidos.size


    inner class PedidosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val status = itemView.findViewById<TextView>(R.id.statusPedido)
        val numero = itemView.findViewById<TextView>(R.id.numeroPedido)
        val vlTotal = itemView.findViewById<TextView>(R.id.vlTotal)
        //val foto = itemView.findViewById<ImageView>(R.id.imageView4)
    }
}