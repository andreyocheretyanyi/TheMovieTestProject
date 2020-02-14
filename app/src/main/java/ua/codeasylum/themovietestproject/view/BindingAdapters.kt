package ua.codeasylum.themovietestproject.view

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.codeasylum.themovietestproject.model.networkDto.Genre
import ua.codeasylum.themovietestproject.model.networkDto.PeopleResult
import ua.codeasylum.themovietestproject.view.adapter.GenreRecyclerAdapter
import ua.codeasylum.themovietestproject.view.adapter.PeopleRecyclerAdapter
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:parentViewModel", "app:selectedGenres", "app:genres", requireAll = true)
    fun bindGenres(
        recyclerView: RecyclerView,
        viewModel: SearchViewModel,
        selectedGenres: MutableList<Genre>,
        genres: MutableList<Genre>
    ) {
        with(recyclerView) {
            if (this.adapter == null) {
                adapter = GenreRecyclerAdapter(viewModel, selectedGenres, genres)
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
            }
            adapter?.notifyDataSetChanged()
        }

    }

    @JvmStatic
    @BindingAdapter("app:parentViewModel", "app:people", requireAll = true)
    fun bindPeople(
        recyclerView: RecyclerView,
        parentViewModel: SearchViewModel,
        people: MutableList<PeopleResult>
    ) {
        with(recyclerView) {
            if (this.adapter == null) {
                adapter = PeopleRecyclerAdapter(parentViewModel, people)
                recyclerView.layoutManager =
                    LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
            }
            adapter?.notifyDataSetChanged()
        }
    }
}