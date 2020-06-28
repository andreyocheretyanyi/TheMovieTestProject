package ua.codeasylum.themovietestproject.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.databinding.FragmentSelectGenresBinding
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

class SelectGenresFragment : BaseFragment<SearchViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return DataBindingUtil.inflate<FragmentSelectGenresBinding>(
            inflater,
            R.layout.fragment_select_genres,
            container,
            false
        ).apply {
            lifecycleOwner = this@SelectGenresFragment.viewLifecycleOwner
            viewModel = this@SelectGenresFragment.viewModel

        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchGenres()
    }

    override fun getClassType(): Class<SearchViewModel> = SearchViewModel::class.java

}