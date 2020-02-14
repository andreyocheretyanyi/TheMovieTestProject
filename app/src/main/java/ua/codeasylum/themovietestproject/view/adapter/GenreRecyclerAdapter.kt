package ua.codeasylum.themovietestproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ua.codeasylum.themovietestproject.databinding.ItemGenreBinding
import ua.codeasylum.themovietestproject.model.networkDto.Genre
import ua.codeasylum.themovietestproject.view.dialog.GenresDialog

class GenreRecyclerAdapter(
    private val parent: GenresDialog,
    private val selectedGenres: MutableList<Genre>,
    val genres: MutableList<Genre>
) : RecyclerView.Adapter<GenreViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemGenreBinding = ItemGenreBinding.inflate(inflater, parent, false)
        return GenreViewHolder(binding.root)
    }

    override fun getItemCount(): Int =
        genres.size


    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.binding!!.genre = genres[position]
        holder.binding.selected = isGenreSelected(genres[position])
        holder.binding.parent = parent
        holder.binding.executePendingBindings()
    }

    private fun isGenreSelected(currentGenre: Genre) = selectedGenres.contains(currentGenre)


}

class GenreViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val binding: ItemGenreBinding? = DataBindingUtil.bind(view)
}