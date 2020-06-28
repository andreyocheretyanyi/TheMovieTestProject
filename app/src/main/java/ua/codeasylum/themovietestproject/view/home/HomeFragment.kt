package ua.codeasylum.themovietestproject.view.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.tabs.TabLayoutMediator
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.BaseFragment
import ua.codeasylum.themovietestproject.databinding.FragmentHomeBinding
import ua.codeasylum.themovietestproject.view.adapter.HomeViewPagerAdapter
import ua.codeasylum.themovietestproject.viewmodel.home.HomeViewModel

class HomeFragment : BaseFragment<HomeViewModel>() {

    private lateinit var vpAdapter: HomeViewPagerAdapter
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        ).apply {
            lifecycleOwner = this@HomeFragment.viewLifecycleOwner
            viewModel = this@HomeFragment.viewModel
            binding = this
            vpAdapter = HomeViewPagerAdapter(childFragmentManager, lifecycle)
            binding.vpHome.adapter = vpAdapter
            setHasOptionsMenu(true)
        }.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        TabLayoutMediator(binding.tabLayout, binding.vpHome) { tab, position ->
            tab.text = viewModel.fragmentNames[position]
        }.attach()
        (activity as AppCompatActivity).supportActionBar?.title = viewModel.title

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.app_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    override fun getClassType(): Class<HomeViewModel> = HomeViewModel::class.java
}