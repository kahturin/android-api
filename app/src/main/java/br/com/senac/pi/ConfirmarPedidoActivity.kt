package br.com.senac.pi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.senac.pi.databinding.ActivityConfirmarPedidoBinding
import br.com.senac.pi.databinding.ActivityTelaProdutoBinding

class ConfirmarPedidoActivity : AppCompatActivity() {
    lateinit var binding: ActivityConfirmarPedidoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmarPedidoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_confirmar_pedido)


        //seprar if por if e dps fazer um if so juntando tudo concatenando com && 

        binding.btnEnviar.setOnClickListener{

            if(binding.TxtCep.text == null){
                binding.InputCep.error = "Preencha esse campo!"
                binding.TxtCep.requestFocus()
            } else {
                binding.InputCep.isErrorEnabled = false
            }

            if(binding.TxtNumero.text == null){
                binding.InputNumero.error = "Preencha esse campo!"
                binding.TxtNumero.requestFocus()
            } else {
                binding.InputNumero.isErrorEnabled = false
            }

            if(binding.TxtComplemento.text == null){
                binding.InputComplemento.error = "Preencha esse campo!"
                binding.TxtComplemento.requestFocus()
            } else {
                binding.InputComplemento.isErrorEnabled = false
            }

            if(binding.TxtBairro.text == null){
                binding.InputBairro.error = "Preencha esse campo!"
                binding.TxtBairro.requestFocus()
            } else {
                binding.InputBairro.isErrorEnabled = false
            }

            if(binding.TxtEndereco.text == null){
                binding.InputEndereco.error = "Preencha esse campo!"
                binding.TxtEndereco.requestFocus()
            } else {
                binding.InputEndereco.isErrorEnabled = false
            }

            if(binding.TxtCidade.text == null){
                binding.InputCidade.error = "Preencha esse campo!"
                binding.TxtCidade.requestFocus()
            } else {
                binding.InputCidade.isErrorEnabled = false
            }

            if(binding.TxtEstado.text == null){
                binding.InputEstado.error = "Preencha esse campo!"
                binding.TxtEstado.requestFocus()
            } else {
                binding.InputEstado.isErrorEnabled = false
            }

//            if(binding.radioGroupPagamento.){
//
//            }

            if(binding.TxtCep.text != null && binding.TxtNumero.text != null && binding.TxtComplemento.text != null
                && binding.TxtBairro.text != null && binding.TxtEndereco.text != null && binding.TxtCidade.text != null && binding.TxtEstado.text != null){

                val i = Intent(this@ConfirmarPedidoActivity, PedidoProcessadoActivity::class.java)
                startActivity(i)

            }

        }


    }
}