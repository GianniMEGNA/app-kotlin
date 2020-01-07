package megna.gianni.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell.view.*

class MyListAdapter : RecyclerView.Adapter< MyListAdapter.ViewHolder >() {

    var list : List<Tasks> = emptyList()
    var userList : List<User> = emptyList()

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.cellTextUser.text = list[position].title
        holder.itemView.cellText.text = list[position].description
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}