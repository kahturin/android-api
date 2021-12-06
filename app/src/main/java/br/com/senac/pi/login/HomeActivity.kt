package br.com.senac.pi.login

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.senac.pi.R
import br.com.senac.pi.databinding.ActivityHomeBinding
import br.com.senac.pi.login.adapter.AdapterCategoria
import br.com.senac.pi.login.model.Categoria
import br.com.senac.pi.login.model.Produto
import br.com.senac.pi.login.model.ProdutosCategoria
import br.com.senac.pi.login.servicos.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    var categoriasResponse: List<Categoria> = listOf<Categoria>()
    val listProdutosCategoria = arrayListOf<ProdutosCategoria>()
    val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    val rt: Retrofit? = Retrofit.Builder().baseUrl(url).addConverterFactory(
        GsonConverterFactory.create()
    ).client(client).build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.pesquisa)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("",false)
                searchItem.collapseActionView()

//                Toast.makeText(this@HomeActivity, "Looking for $query", Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { pesquisarProdutos(it) }
//                Toast.makeText(this@HomeActivity, "Looking for $newText", Toast.LENGTH_SHORT).show()
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()
        getCategorias()
    }

    fun getCategorias() {
        rt?.let {
            val servicoCategorias = rt.create(CategoriaService::class.java)
            val callCategorias: Call<List<Categoria>> = servicoCategorias.getCategorias()

            val callbackCategorias = object : Callback<List<Categoria>> {
                override fun onResponse(
                    call: Call<List<Categoria>>,
                    response: Response<List<Categoria>>
                ) {
                    Log.d("homeActivity", "Response Categorias: ${response.body()}")
                    if (response.isSuccessful) {
                        val categorias = response.body()
                        categorias?.let {
                            fillCategoriasResponse(it)
                            Log.i("Categorias", "Categorias: $categoriasResponse")
                            getProdutos()
                        }
                    } else {
                        Toast.makeText(
                            this@HomeActivity,
                            "Falha ao buscar produtos",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<Categoria>?>, t: Throwable) {
                    Log.e("InfoUserActivity", "Perfil", t)
                }
            }
            callCategorias.enqueue(callbackCategorias)
        }
    }

    private fun getProdutos() {
        val servicoProdutos = rt?.create(ProdutoService::class.java)
        val callProdutos: Call<List<Produto>>? = servicoProdutos?.getProdutos()
        val callbackProdutos = object : Callback<List<Produto>> {
            override fun onResponse(
                call: Call<List<Produto>>,
                response: Response<List<Produto>>
            ) {
                Log.d("homeActivity", "Response Products: ${response.body()}")
                if (response.isSuccessful) {
                    val produtos = response.body()
                    produtos?.let {
                        Log.i("Categorias", "Categorias: $produtos")
                        reloadListProdutos(produtos)
                    }
                } else {
                    Toast.makeText(
                        this@HomeActivity,
                        "Falha ao buscar produtos",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Produto>?>, t: Throwable) {
                Log.e("InfoUserActivity", "Perfil", t)
            }
        }
        callProdutos?.enqueue(callbackProdutos)
    }

    private fun fillCategoriasResponse(categorias: List<Categoria>) {
        this.categoriasResponse = categorias
    }

    fun reloadListProdutos(produtos: List<Produto>?) {
        listProdutosCategoria.clear()

        val listProducts = arrayListOf<Produto>()
        val listCategria = arrayListOf<Categoria>()
        categoriasResponse.forEach { categoria ->
            produtos?.forEach{ produto ->
                if (produto.id_categoria == categoria.id){
                    listProducts.add(produto)
                    listCategria.add(categoria)
                }
            }
            if(listCategria.isNotEmpty()){
                listProdutosCategoria.add(ProdutosCategoria(categoria, listProducts))
            }
            listCategria.clear()
        }

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycleViewCategoria.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleViewCategoria.setHasFixedSize(true)

        //Configurando o Adapter
        val adapterCategoria = AdapterCategoria(this, listProdutosCategoria)
        Log.i("ProdutosCategorias", "ProdutosCategorias: $listProducts")
        binding.recycleViewCategoria.adapter = adapterCategoria
    }


    fun pesquisarProdutos(nome: String="") {
        rt?.let {rt ->
            nome?.let { nome->
                val servico = rt.create(ProdutoService::class.java)
                val call: Call<List<Produto>> = servico.procurarProduto(nome)

                val callback = object : Callback<List<Produto>> {
                    override fun onResponse(
                        call: Call<List<Produto>>,
                        response: Response<List<Produto>>
                    ) {
                        if (response.isSuccessful) {
                            val produtos = response.body()
                            produtos?.let {
                                reloadListProdutos(produtos)
                            }
                        } else {
                            Toast.makeText(
                                this@HomeActivity,
                                "Falha ao buscar produtos com $nome",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<List<Produto>?>, t: Throwable) {
                        Log.e("InfoUserActivity", "Perfil", t)
                    }
                }
                call.enqueue(callback)
            }
        }
    }

    fun pesquisarPorCategoria(idCategoria: Int) {
        rt?.let {
            val servico = rt.create(ProdutoService::class.java)
            val call: Call<List<Produto>> = servico.produtosCategoria(idCategoria)

            val callback = object : Callback<List<Produto>> {
                override fun onResponse(
                    call: Call<List<Produto>>,
                    response: Response<List<Produto>>
                ) {
                    if (response.isSuccessful) {
                        val produtos = response.body()
                        produtos?.let {
//                            reloadListCategorias(produtos)
                        }
                    } else {
                        Toast.makeText(
                            this@HomeActivity,
                            "Falha ao buscar produtos da categoria $idCategoria",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<List<Produto>?>, t: Throwable) {
                    Log.e("InfoUserActivity", "Perfil", t)
                }
            }
            call.enqueue(callback)
        }
    }
}