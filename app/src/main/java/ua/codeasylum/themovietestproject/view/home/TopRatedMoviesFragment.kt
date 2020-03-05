package ua.codeasylum.themovietestproject.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ua.codeasylum.themovietestproject.view.ListFragment
import ua.codeasylum.themovietestproject.viewmodel.home.TopMoviesViewModel

class TopRatedMoviesFragment : ListFragment() {
    
    private lateinit var topRatedMoviesViewModel : TopMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = getBinding(inflater, container).apply {
        topRatedMoviesViewModel =
            ViewModelProvider(
                activity!!.viewModelStore,
                factory
            )[TopMoviesViewModel::class.java]
        lifecycleOwner = this@TopRatedMoviesFragment.viewLifecycleOwner
        viewModel = topRatedMoviesViewModel
        topRatedMoviesViewModel.initMovieListDataFactory()

    }.root
}