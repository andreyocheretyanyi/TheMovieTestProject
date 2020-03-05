package ua.codeasylum.themovietestproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.databinding.FragmentMoviesListBinding
import ua.codeasylum.themovietestproject.viewmodel.MovieListViewModel

abstract class ListFragment : BaseFragment() {

    protected fun getBinding(inflater: LayoutInflater, container: ViewGroup?) : FragmentMoviesListBinding =
        DataBindingUtil.inflate(
        inflater,
        R.layout.fragment_movies_list,
        container,
        false
    )

}