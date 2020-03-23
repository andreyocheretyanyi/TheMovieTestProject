package ua.codeasylum.themovietestproject.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ua.codeasylum.themovietestproject.R
import ua.codeasylum.themovietestproject.model.networkDto.Genre
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.model.networkDto.Person
import ua.codeasylum.themovietestproject.view.adapter.GenreRecyclerAdapter
import ua.codeasylum.themovietestproject.view.adapter.MovieRecyclerAdapter
import ua.codeasylum.themovietestproject.view.adapter.PeopleRecyclerAdapter
import ua.codeasylum.themovietestproject.viewmodel.MovieListViewModel
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
    @BindingAdapter("app:parentViewModel", "app:people", "app:haveToNotify", requireAll = true)
    fun bindPeople(
        recyclerView: RecyclerView,
        parentViewModel: SearchViewModel,
        pagedList: PagedList<Person>?,
        haveToNotify: Int
    ) {
        with(recyclerView) {
            if (this.adapter == null) {
                adapter = PeopleRecyclerAdapter(parentViewModel)
                recyclerView.layoutManager =
                    GridLayoutManager(recyclerView.context, 3)
            }
            pagedList?.apply {
                (adapter as PeopleRecyclerAdapter).submitList(pagedList)
            }

        }
    }

    @JvmStatic
    @BindingAdapter("app:parentViewModel", "app:movies", "app:haveToNotify", requireAll = true)
    fun bindFoundMovies(
        recyclerView: RecyclerView,
        parentViewModel: MovieListViewModel,
        pagedList: PagedList<MovieResult>?,
        haveToNotify: Int
    ) {
        with(recyclerView) {
            if (this.adapter == null) {
                adapter = MovieRecyclerAdapter(parentViewModel)
                recyclerView.layoutManager =
                    GridLayoutManager(recyclerView.context, 2)
            }
            pagedList?.apply {
                (adapter as MovieRecyclerAdapter).submitList(pagedList)
            }

        }
    }

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun bindImageUrl(imageView: ImageView, url: String?) {
        Picasso.get()
            .load("https://image.tmdb.org/t/p/original${url ?: ""}")
            .placeholder(R.drawable.movies_paceholder)
            .resize(400, 600)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("app:visibleElseGone")
    fun visibleElseGone(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}