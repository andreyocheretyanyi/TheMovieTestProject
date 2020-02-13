package ua.codeasylum.themovietestproject.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import javax.inject.Inject

open class BaseFragment : Fragment() {

    protected lateinit var factory : ViewModelFactory
    @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectSelf()
    }


    private fun injectSelf() {
        (activity as BaseActivity).activityComponent.inject(this)
    }
}