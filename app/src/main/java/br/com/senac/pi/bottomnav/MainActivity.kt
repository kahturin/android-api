package br.com.senac.pi.bottomnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var frag = HomeFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, frag).commit()

        binding.bottomNavigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.home -> {
                   val frag = HomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, frag)
                }

                R.id.favoritos -> {
                   val frag = FavoritosFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, frag)
                }

                R.id.minhas_compras -> {
                    val frag = MinhasComprasFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, frag)
                }

                R.id.mais -> {
                    val frag = MaisFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, frag)
                }
            }

            true
        }
    }
}