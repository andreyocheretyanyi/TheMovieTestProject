package ua.codeasylum.themovietestproject.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ua.codeasylum.themovietestproject.base.showToast
import ua.codeasylum.themovietestproject.view.ListFragment
import ua.codeasylum.themovietestproject.viewmodel.SearchResultViewModel

class SearchResultFragment : ListFragment() {

    private lateinit var searchResultViewModel: SearchResultViewModel
    private val args: SearchResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = getBinding(inflater, container!!).apply {
        searchResultViewModel =
            ViewModelProvider(
                activity!!.viewModelStore,
                factory
            )[SearchResultViewModel::class.java]
        lifecycleOwner = this@SearchResultFragment.viewLifecycleOwner
        viewModel = searchResultViewModel
        searchResultViewModel.args = args
        searchResultViewModel.initMovieListDataFactory()

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchResultViewModel.error.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                showToast(it)
                searchResultViewModel.error.value = ""
            }
        })
    }


}