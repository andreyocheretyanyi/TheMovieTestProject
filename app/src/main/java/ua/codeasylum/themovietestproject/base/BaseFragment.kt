package ua.codeasylum.themovietestproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ua.codeasylum.themovietestproject.viewmodel.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel> : InjectableFragment() {

    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = super.onCreateView(inflater, container, savedInstanceState)
        viewModel =
            ViewModelProvider(
                activity!!.viewModelStore,
                factory
            )[getClassType()]
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeError()
    }

    private fun observeError() {
        viewModel.error.observe(this.viewLifecycleOwner, Observer {
            it.message?.apply {
                showToast(this)
            }
        })
    }

    abstract fun getClassType(): Class<VM>

}