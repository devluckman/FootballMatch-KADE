package com.man.hellosport.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.hellosport.R
import com.man.hellosport.model.teams.Teams
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_teams.view.*

class TeamsAdapter(val teams : List<Teams>,
                   private val clicklistener: (Teams) -> Unit) : RecyclerView.Adapter<TeamsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_teams, parent, false))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(teams[position], clicklistener)
    }

    inner class MyViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {
        fun bind(teams : Teams, clicklistener: (Teams)-> Unit){

            Picasso.get()
                .load(teams.strTeamBadge)
                .into(itemView.teamsLogo)

            itemView.strTeams.text = teams.strTeam

            itemView.cvTeams.setOnClickListener {
                clicklistener(teams)
            }

        }
    }
}