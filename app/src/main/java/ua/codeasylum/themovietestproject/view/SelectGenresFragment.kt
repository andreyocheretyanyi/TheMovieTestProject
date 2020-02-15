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
import ua.codeasylum.themovietestproject.databinding.FragmentSelectGenresBinding
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

class SelectGenresFragment : BaseFragment() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<FragmentSelectGenresBinding>(
        inflater,
        R.layout.fragment_select_genres,
        container,
        false
    ).apply {
        lifecycleOwner = this@SelectGenresFragment.viewLifecycleOwner
        searchViewModel =
            ViewModelProvider(activity!!.viewModelStore, factory)[SearchViewModel::class.java]
        viewModel = searchViewModel

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.fetchGenres()
        searchViewModel.closeSelectGenreFragment.observe(viewLifecycleOwner, Observer {
            if (it) {
                searchViewModel.closeSelectGenreFragment.value = false
                findNavController().navigateUp()
            }
        })
    }

}