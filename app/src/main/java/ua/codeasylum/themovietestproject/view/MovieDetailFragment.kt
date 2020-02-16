package ua.codeasylum.themovietestproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.base.showToast
import ua.codeasylum.themovietestproject.databinding.FragmentMovieDetailBinding
import ua.codeasylum.themovietestproject.viewmodel.MovieDetailViewModel

class MovieDetailFragment : BaseFragment() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel
    private val movieDetailFragmentArgs: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<FragmentMovieDetailBinding>(
        inflater,
        R.layout.fragment_movie_detail,
        container,
        false
    ).apply {
        movieDetailViewModel =
            ViewModelProvider(activity!!.viewModelStore, factory)[MovieDetailViewModel::class.java]
        lifecycleOwner = this@MovieDetailFragment.viewLifecycleOwner
        viewModel = movieDetailViewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailViewModel.performArgs(movieDetailFragmentArgs)
        movieDetailViewModel.error.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                showToast(it)
                movieDetailViewModel.error.value = ""
            }
        })
    }
}