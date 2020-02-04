package com.man.hellosport.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.hellosport.R
import com.man.hellosport.model.standing.TableItem
import kotlinx.android.synthetic.main.item_standing.view.*

class StandingAdapter(val tableItem: List<TableItem>)
    : RecyclerView.Adapter<StandingAdapter.MyViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_standing, parent, false))
    }

    override fun getItemCount(): Int = tableItem.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(tableItem[position], position + 1)
    }

    inner class MyViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {
        fun bind(tableItem : TableItem, no : Int){

            itemView.txtNo.text = no.toString()
            itemView.txtName.text = tableItem.name
            itemView.txtPlayed.text = tableItem.played.toString()
            itemView.txtWin.text = tableItem.win.toString()
            itemView.txtDraw.text = tableItem.draw.toString()
            itemView.txtLoss.text = tableItem.loss.toString()
            itemView.txtPoint.text = tableItem.total.toString()


        }
    }
}