package ua.codeasylum.themovietestproject.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ua.codeasylum.themovietestproject.view.ListFragment
import ua.codeasylum.themovietestproject.viewmodel.home.AllMoviesViewModel

class AllMoviesFragment : ListFragment() {
    
    private lateinit var allMoviesViewModel : AllMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = getBinding(inflater, container).apply {
        allMoviesViewModel =
            ViewModelProvider(
                activity!!.viewModelStore,
                factory
            )[AllMoviesViewModel::class.java]
        lifecycleOwner = this@AllMoviesFragment.viewLifecycleOwner
        viewModel = allMoviesViewModel
        allMoviesViewModel.initMovieListDataFactory()

    }.root
}