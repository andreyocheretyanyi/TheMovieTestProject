package ua.codeasylum.themovietestproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ua.codeasylum.themovietestproject.databinding.ItemPersonBinding
import ua.codeasylum.themovietestproject.model.networkDto.Person
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

class PeopleRecyclerAdapter(val parentViewModel: SearchViewModel) :
    PagedListAdapter<Person, PeopleViewHolder>(
        UserDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflater = LayoutInflater.from(parent.context)


        val binding: ItemPersonBinding = ItemPersonBinding.inflate(inflater, parent, false)
        return PeopleViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.personBinding?.apply {
            person = getItem(position)
            parentViewModel = this@PeopleRecyclerAdapter.parentViewModel
        }

    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
                oldItem == newItem


        }
    }

}

class PeopleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val personBinding: ItemPersonBinding? = DataBindingUtil.bind(view)
}