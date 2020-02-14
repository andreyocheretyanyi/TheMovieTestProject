package ua.codeasylum.themovietestproject.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.base.notifyObserver
import ua.codeasylum.themovietestproject.databinding.DialogSelectGenresBinding
import ua.codeasylum.themovietestproject.model.networkDto.Genre
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

class GenresDialog(
    private val delegateViewModel: SearchViewModel
) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<DialogSelectGenresBinding>(
        inflater,
        R.layout.dialog_select_genres,
        container,
        false
    ).apply {
        dialog = this@GenresDialog
        delegateViewModel = this@GenresDialog.delegateViewModel
        lifecycleOwner = this@GenresDialog.viewLifecycleOwner
    }.root

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    fun onSelectSingleGenre(genre: Genre) {
        delegateViewModel.selectedGenres.value?.add(genre)
        delegateViewModel.selectedGenres.notifyObserver()
    }

    fun clearClick() {
        delegateViewModel.selectedGenres.value?.clear()
        delegateViewModel.selectedGenres.notifyObserver()
    }

    fun selectAndExitClick() {
        delegateViewModel.onGenresSelected()
        dismiss()
    }
}