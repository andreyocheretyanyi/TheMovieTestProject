package ua.codeasylum.themovietestproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ua.codeasylum.themovietestproject.databinding.ItemPersonBinding
import ua.codeasylum.themovietestproject.model.networkDto.PeopleResult
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

class PeopleRecyclerAdapter(val parentViewModel: SearchViewModel) :
    PagedListAdapter<PeopleResult, PeopleViewHolder>(
        UserDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflater = LayoutInflater.from(parent.context)


        val binding: ItemPersonBinding = ItemPersonBinding.inflate(inflater, parent, false)
        return PeopleViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.personBinding?.person = getItem(position)
    }

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<PeopleResult>() {
            override fun areItemsTheSame(oldItem: PeopleResult, newItem: PeopleResult): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: PeopleResult, newItem: PeopleResult): Boolean =
                oldItem == newItem


        }
    }

}

class PeopleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val personBinding: ItemPersonBinding? = DataBindingUtil.bind(view)
}