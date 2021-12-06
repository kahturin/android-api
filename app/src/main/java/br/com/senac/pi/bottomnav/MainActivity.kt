package br.com.senac.pi.bottomnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityMainBinding
import br.com.senac.pi.fragments.CarrinhoFragment
import br.com.senac.pi.fragments.HomeFragment
import br.com.senac.pi.fragments.PedidosFragment
import br.com.senac.pi.fragments.PerfilFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class  MainActivity : AppCompatActivity() {
    val home = HomeFragment()
    val pedidos = PedidosFragment()
    val carrinho = CarrinhoFragment()
    val perfil = PerfilFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bt = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        redirectFragment(home)

        bt.setOnItemSelectedListener{

            when(it.itemId){
                R.id.ic_home -> redirectFragment(home)

                R.id.ic_pedidos -> redirectFragment(pedidos)

                R.id.ic_perfil -> redirectFragment(perfil)

                R.id.ic_carrinho -> redirectFragment(carrinho)
            }

            true
        }
    }
    private fun redirectFragment(frag: Fragment){
        frag?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, frag).commit()
        }
    }
}
