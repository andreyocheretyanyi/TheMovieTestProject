package ua.codeasylum.themovietestproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.databinding.FragmentSearchMoviesBinding
import ua.codeasylum.themovietestproject.view.dialog.GenresDialog
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

class SearchFragment : BaseFragment() {

    lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]
        return DataBindingUtil.inflate<FragmentSearchMoviesBinding>(
            inflater,
            R.layout.fragment_search_movies,
            container,
            false
        ).apply {
            lifecycleOwner = this@SearchFragment.viewLifecycleOwner
            viewModel = searchViewModel
            this@SearchFragment.viewLifecycleOwner.lifecycle.addObserver(searchViewModel)
            showDialogHandler = this@SearchFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.showDialog.observe(this.viewLifecycleOwner, Observer {
            if (it) showGenresDialog()
        })
    }


    private fun showGenresDialog() {
        GenresDialog(searchViewModel).show(
            childFragmentManager,
            GenresDialog::class.java.simpleName
        )
    }
}