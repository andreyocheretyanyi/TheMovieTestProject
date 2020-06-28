package ua.codeasylum.themovietestproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.databinding.FragmentMoviesListBinding
import ua.codeasylum.themovietestproject.viewmodel.BaseViewModel
import ua.codeasylum.themovietestproject.viewmodel.MovieListViewModel

abstract class ListFragment<VM : BaseViewModel> : BaseFragment<VM>() {

    protected fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMoviesListBinding =
        DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movies_list,
            container,
            false
        )

    abstract fun getViewModel(): MovieListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}