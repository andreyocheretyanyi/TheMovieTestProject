package ua.codeasylum.themovietestproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.databinding.FragmentSearchPeopleBinding
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

class SearchPeopleFragment : BaseFragment() {

    lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<FragmentSearchPeopleBinding>(
        inflater,
        R.layout.fragment_search_people,
        container,
        false
    )
        .apply {
            lifecycleOwner = this@SearchPeopleFragment.viewLifecycleOwner
            searchViewModel = ViewModelProvider(
                activity!!.viewModelStore,
                factory
            )[SearchViewModel::class.java]
            viewModel = searchViewModel
        }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.subscribeTextChange()
        searchViewModel.enteredPersonName.observe(
            this.viewLifecycleOwner,
            searchViewModel.enteredPersonNameObserver
        )
    }
}