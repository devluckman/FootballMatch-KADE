package com.man.hellosport.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.hellosport.R
import com.man.hellosport.model.league.LeaguesItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_league.view.*

class LeagueAdapter(val league : List<LeaguesItem>,
                    private val clicklistener: (LeaguesItem) -> Unit)
    : RecyclerView.Adapter<LeagueAdapter.MyViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_league, parent, false))
    }

    override fun getItemCount(): Int = league.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(league[position], clicklistener)
    }

    inner class MyViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {
        fun bind(league : LeaguesItem, clicklistener: (LeaguesItem)-> Unit){

            Picasso.get()
                .load(league.strLogo)
                .into(itemView.logoLeague)

            itemView.strLeague.text = league.strLeague
            itemView.cvLeague.setOnClickListener {
                clicklistener(league)
            }
        }
    }
}