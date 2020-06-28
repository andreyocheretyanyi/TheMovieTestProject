package ua.codeasylum.themovietestproject.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.codeasylum.themovietestproject.view.ListFragment
import ua.codeasylum.themovietestproject.viewmodel.MovieListViewModel
import ua.codeasylum.themovietestproject.viewmodel.home.NewestMovieViewModel

class UpcomingMoviesFragment : ListFragment<NewestMovieViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return getBinding(inflater, container).apply {
            lifecycleOwner = this@UpcomingMoviesFragment.viewLifecycleOwner
            viewModel = this@UpcomingMoviesFragment.viewModel

        }.root
    }

    override fun getViewModel(): MovieListViewModel = viewModel
    override fun getClassType(): Class<NewestMovieViewModel> = NewestMovieViewModel::class.java

}