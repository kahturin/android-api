package br.com.senac.pi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.senac.pi.databinding.ActivityPedidoProcessadoBinding
import br.com.senac.pi.login.HomeActivity

class PedidoProcessadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_processado)

        lateinit var binding: ActivityPedidoProcessadoBinding

        binding = ActivityPedidoProcessadoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_confirmar_pedido)


        //seprar if por if e dps fazer um if so juntando tudo concatenando com &&

        binding.btnVoltar.setOnClickListener {
            val i = Intent(this@PedidoProcessadoActivity, HomeActivity::class.java)
            startActivity(i)
        }
    }
}