package ua.codeasylum.themovietestproject.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ua.codeasylum.themovietestproject.view.ListFragment
import ua.codeasylum.themovietestproject.viewmodel.home.NewestMovieViewModel

class UpcomingMoviesFragment : ListFragment() {
    
    
    private lateinit var newestMovieViewModel: NewestMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = getBinding(inflater, container).apply {
        newestMovieViewModel =
            ViewModelProvider(
                activity!!.viewModelStore,
                factory
            )[NewestMovieViewModel::class.java]
        lifecycleOwner = this@UpcomingMoviesFragment.viewLifecycleOwner
        viewModel = newestMovieViewModel

    }.root
}