package ua.codeasylum.themovietestproject.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.base.showToast
import ua.codeasylum.themovietestproject.databinding.FragmentSearchMoviesBinding
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

class SearchFragment : BaseFragment() {

    lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<FragmentSearchMoviesBinding>(
        inflater,
        R.layout.fragment_search_movies,
        container,
        false
    ).apply {
        searchViewModel =
            ViewModelProvider(activity!!.viewModelStore, factory)[SearchViewModel::class.java]
        lifecycleOwner = this@SearchFragment.viewLifecycleOwner
        viewModel = searchViewModel
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.error.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                showToast(it)
                searchViewModel.error.value = ""
            }
        })
        (activity as AppCompatActivity).supportActionBar?.title = searchViewModel.searchTitle
    }


}