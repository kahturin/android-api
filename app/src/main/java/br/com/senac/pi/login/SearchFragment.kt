package br.com.senac.pi.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.senac.pi.R
import br.com.senac.pi.databinding.FragmentSearchBinding

private const val ARG_SEARCH = "param1"

class SearchFragment : Fragment() {
    private var param1: String? = null
    lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_SEARCH)
        }
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        binding = FragmentSearchBinding.inflate(inflater)
//    }

    companion object {
        @JvmStatic
        fun newInstance(searchValue: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SEARCH, searchValue)
                }
            }
    }
}