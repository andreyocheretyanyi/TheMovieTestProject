package ua.codeasylum.themovietestproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
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
        searchViewModel.openSelectGenre.observe(this.viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(R.id.action_searchFragment_to_selectGenresFragment)
                searchViewModel.openSelectGenre.value = false
            }

        })
        searchViewModel.openPersonSearch.observe(this.viewLifecycleOwner, Observer {
            if (it) {
                searchViewModel.openPersonSearch.value = false
                findNavController().navigate(R.id.action_searchFragment_to_searchPeopleFragment)
            }
        })
    }


}