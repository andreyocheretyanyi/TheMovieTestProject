package ua.codeasylum.themovietestproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.databinding.FragmentMovieDetailBinding
import ua.codeasylum.themovietestproject.viewmodel.MovieDetailViewModel

class MovieDetailFragment : BaseFragment<MovieDetailViewModel>() {

    private val movieDetailFragmentArgs: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return DataBindingUtil.inflate<FragmentMovieDetailBinding>(
            inflater,
            R.layout.fragment_movie_detail,
            container,
            false
        ).apply {
            lifecycleOwner = this@MovieDetailFragment.viewLifecycleOwner
            viewModel = this@MovieDetailFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.performArgs(movieDetailFragmentArgs)
        (activity as AppCompatActivity).supportActionBar?.title = viewModel.movieTitle.value

    }

    override fun getClassType(): Class<MovieDetailViewModel> = MovieDetailViewModel::class.java
}