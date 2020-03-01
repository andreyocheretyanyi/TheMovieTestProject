package ua.codeasylum.themovietestproject.view

import android.os.Bundle
import android.view.View
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.viewmodel.ListViewModel

abstract class ListFragment : BaseFragment() {
    private lateinit var listViewModel: ListViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listViewModel.fetchData()
    }
}