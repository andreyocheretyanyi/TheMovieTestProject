package ua.codeasylum.themovietestproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.databinding.FragmentHomeBinding
import ua.codeasylum.themovietestproject.viewmodel.home.HomeViewModel

class HomeFragment : BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<FragmentHomeBinding>(
        inflater,
        R.layout.fragment_home,
        container,
        false
    ).apply {
        homeViewModel =
            ViewModelProvider(activity!!.viewModelStore, factory)[homeViewModel::class.java]
        lifecycleOwner = this@HomeFragment.viewLifecycleOwner
        viewModel = homeViewModel
        binding = this
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        TabLayoutMediator(binding.tabLayout, binding.vpHome) { tab, position ->
            tab.text = homeViewModel.fragmentNames[position]
        }.attach()
    }
}