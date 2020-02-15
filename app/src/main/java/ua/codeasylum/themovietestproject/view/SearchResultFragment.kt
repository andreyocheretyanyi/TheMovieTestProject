package ua.codeasylum.themovietestproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.databinding.FragmentSearchResultBinding
import ua.codeasylum.themovietestproject.viewmodel.SearchResultViewModel

class SearchResultFragment : BaseFragment() {

    private lateinit var searchResultViewModel: SearchResultViewModel
    private val args: SearchResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =

        DataBindingUtil.inflate<FragmentSearchResultBinding>(
            inflater,
            R.layout.fragment_search_result,
            container,
            false
        ).apply {
            searchResultViewModel =
                ViewModelProvider(
                    activity!!.viewModelStore,
                    factory
                )[SearchResultViewModel::class.java]
            lifecycleOwner = this@SearchResultFragment.viewLifecycleOwner
            viewModel = searchResultViewModel
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchResultViewModel.initMovieDataFactory(args.year,args.filmQuery,args.genresIds,args.personId,args.isAdult)

    }


}