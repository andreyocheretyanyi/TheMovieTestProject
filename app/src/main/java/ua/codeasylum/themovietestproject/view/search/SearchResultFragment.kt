package ua.codeasylum.themovietestproject.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import ua.codeasylum.themovietestproject.view.ListFragment
import ua.codeasylum.themovietestproject.viewmodel.MovieListViewModel
import ua.codeasylum.themovietestproject.viewmodel.SearchResultViewModel

class SearchResultFragment : ListFragment<SearchResultViewModel>() {

    private val args: SearchResultFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return getBinding(inflater, container!!).apply {
            this@SearchResultFragment.viewModel.args = args
            lifecycleOwner = this@SearchResultFragment.viewLifecycleOwner
            this@SearchResultFragment.viewModel
        }.root
    }

    override fun getViewModel(): MovieListViewModel =
        viewModel

    override fun getClassType(): Class<SearchResultViewModel> = SearchResultViewModel::class.java


}