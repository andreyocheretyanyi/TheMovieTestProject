package ua.codeasylum.themovietestproject.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.base.showToast
import ua.codeasylum.themovietestproject.databinding.FragmentSearchPeopleBinding
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

class SearchPersonFragment : BaseFragment() {

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
            lifecycleOwner = this@SearchPersonFragment.viewLifecycleOwner
            searchViewModel = ViewModelProvider(
                activity!!.viewModelStore,
                factory
            )[SearchViewModel::class.java]
            viewModel = searchViewModel
        }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.personSearchedName.observe(
            this.viewLifecycleOwner,
            searchViewModel.enteredPersonNameObserver
        )
        searchViewModel.error.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                showToast(it)
                searchViewModel.error.value = it
            }
        })
    }
}