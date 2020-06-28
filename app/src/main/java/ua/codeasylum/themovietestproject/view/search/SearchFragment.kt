package ua.codeasylum.themovietestproject.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.databinding.FragmentSearchMoviesBinding
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

class SearchFragment : BaseFragment<SearchViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return DataBindingUtil.inflate<FragmentSearchMoviesBinding>(
            inflater,
            R.layout.fragment_search_movies,
            container,
            false
        ).apply {
            lifecycleOwner = this@SearchFragment.viewLifecycleOwner
            viewModel = this@SearchFragment.viewModel
        }.root
    }

    override fun getClassType(): Class<SearchViewModel> = SearchViewModel::class.java




}