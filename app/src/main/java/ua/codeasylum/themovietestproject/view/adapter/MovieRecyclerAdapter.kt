package ua.codeasylum.themovietestproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ua.codeasylum.themovietestproject.databinding.ItemMovieBinding
import ua.codeasylum.themovietestproject.model.networkDto.MovieResult
import ua.codeasylum.themovietestproject.viewmodel.SearchResultViewModel

class MovieRecyclerAdapter(val parentViewModel: SearchResultViewModel) :
    PagedListAdapter<MovieResult, MovieViewHolder>(
        movieDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemMovieBinding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.movieResultBinding?.apply {
            movieResult = getItem(position)
            parentViewModel = this@MovieRecyclerAdapter.parentViewModel
        }

    }

    companion object {
        val movieDiffCallback = object : DiffUtil.ItemCallback<MovieResult>() {
            override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean =
                oldItem == newItem


        }
    }

}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val movieResultBinding: ItemMovieBinding? = DataBindingUtil.bind(view)
}