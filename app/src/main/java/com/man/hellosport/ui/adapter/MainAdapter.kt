package com.man.hellosport.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.hellosport.R
import com.man.hellosport.model.Events
import com.man.hellosport.utils.FormatDate
import kotlinx.android.synthetic.main.item_matchlist.view.*

class MainAdapter (val matchs : List<Events>,
                   private val clicklistener: (Events) -> Unit)
    : RecyclerView.Adapter<MainAdapter.MyViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_matchlist, parent, false))
    }

    override fun getItemCount(): Int = matchs.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(matchs[position], clicklistener)
    }

    inner class MyViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {
        fun bind(match : Events, clicklistener: (Events)-> Unit){
            if (match.intHomeScore == null){
                itemView.dateMatch.setTextColor(itemView.context.resources.getColor(R.color.time_nextmatch))
            }
            itemView.dateMatch.text = FormatDate.getLongDate(match.dateEvent!!)
            itemView.tvListHomeName.text = match.strHomeTeam
            itemView.tvListHomeScore.text = match.intHomeScore
            itemView.tvListAwayName.text = match.strAwayTeam
            itemView.tvListAwayScore.text = match.intAwayScore
            itemView.cvMatch.setBackgroundResource(R.drawable.grad)
            itemView.cvMatch.setOnClickListener {
                clicklistener(match)
            }

        }
    }
}