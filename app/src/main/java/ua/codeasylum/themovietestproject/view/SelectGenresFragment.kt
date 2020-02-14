package ua.codeasylum.themovietestproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.databinding.FragmentSelectGenresBinding
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

class SelectGenresFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<FragmentSelectGenresBinding>(
        inflater,
        R.layout.fragment_select_genres,
        container,
        false
    ).apply {
        lifecycleOwner = this@SelectGenresFragment.viewLifecycleOwner
        viewModel =
            ViewModelProvider(activity!!.viewModelStore, factory)[SearchViewModel::class.java]

    }.root

}