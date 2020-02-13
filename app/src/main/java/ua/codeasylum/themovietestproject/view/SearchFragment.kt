package ua.codeasylum.themovietestproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

class SearchFragment : BaseFragment() {

    lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}