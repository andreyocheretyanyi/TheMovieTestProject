package ua.codeasylum.themovietestproject.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ua.codeasylum.themovietestproject.view.home.AllMoviesFragment
import ua.codeasylum.themovietestproject.view.home.TopRatedMoviesFragment
import ua.codeasylum.themovietestproject.view.home.UpcomingMoviesFragment

private const val fragmentCount = 3

class HomeViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int = fragmentCount


    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> AllMoviesFragment()
            1 -> TopRatedMoviesFragment()
            2 -> UpcomingMoviesFragment()
            else -> AllMoviesFragment()
        }

}