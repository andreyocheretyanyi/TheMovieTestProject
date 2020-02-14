package ua.codeasylum.themovietestproject.view

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.codeasylum.themovietestproject.model.networkDto.Genre
import ua.codeasylum.themovietestproject.view.adapter.GenreRecyclerAdapter
import ua.codeasylum.themovietestproject.view.dialog.GenresDialog

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:parent", "app:selectedGenres", "app:genres", requireAll = true)
    fun bindGenres(
        recyclerView: RecyclerView,
        dialog: GenresDialog,
        selectedGenres: MutableList<Genre>,
        genres: MutableList<Genre>
    ) {
        with(recyclerView) {
            if (this.adapter == null) {
                adapter = GenreRecyclerAdapter(dialog, selectedGenres, genres)
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
            }
            adapter?.notifyDataSetChanged()
        }

    }
}