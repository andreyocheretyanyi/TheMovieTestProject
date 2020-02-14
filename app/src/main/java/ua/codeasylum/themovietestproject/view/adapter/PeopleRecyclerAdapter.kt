package ua.codeasylum.themovietestproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ua.codeasylum.themovietestproject.databinding.ItemPersonBinding
import ua.codeasylum.themovietestproject.model.networkDto.PeopleResult
import ua.codeasylum.themovietestproject.viewmodel.SearchViewModel

class PeopleRecyclerAdapter(val parentViewModel: SearchViewModel, val people: List<PeopleResult>) :
    RecyclerView.Adapter<PeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemPersonBinding = ItemPersonBinding.inflate(inflater, parent, false)
        return PeopleViewHolder(binding.root)
    }

    override fun getItemCount(): Int = people.size


    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.personBinding?.person = people[position]
    }

}

class PeopleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val personBinding: ItemPersonBinding? = DataBindingUtil.bind(view)
}