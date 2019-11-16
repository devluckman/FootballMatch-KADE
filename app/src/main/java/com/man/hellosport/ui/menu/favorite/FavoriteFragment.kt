package com.man.hellosport.ui.menu.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.man.hellosport.R
import com.man.hellosport.data.local.Favorite
import com.man.hellosport.data.local.database
import com.man.hellosport.model.Events
import com.man.hellosport.ui.adapter.MainAdapter
import com.man.hellosport.ui.detail.DetailEventActivity
import com.man.hellosport.utils.invisible
import com.man.hellosport.utils.visible
import kotlinx.android.synthetic.main.schedulematch_layout.*
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity


class FavoriteFragment : Fragment() {

    private lateinit var adapter: MainAdapter
    private var events : MutableList<Events> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.schedulematch_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun getFromLocal() {
        activity?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(parser = rowParser { id : Long, eventid : String, dateEvent : String,
                idHome : String, strHomeName : String, homeScore : String, homeGoals : String, homeGk : String, homeDef : String, homeMid : String, homeFor : String,
                idAway : String, strAwayName : String, awayScore : String, awayGoals : String, awayGk : String, awayDef : String, awayMid : String, awayFor : String
                ->
                Events(id = id, idEvent = eventid, dateEvent = dateEvent,
                idHomeTeam = idHome, strHomeTeam =  strHomeName, intHomeScore = homeScore, strHomeGoalDetails = homeGoals, strHomeLineupGoalkeeper = homeGk, strHomeLineupDefense = homeDef, strHomeLineupMidfield = homeMid, strHomeLineupForward = homeFor,
                idAwayTeam = idAway, strAwayTeam =  strAwayName, intAwayScore = awayScore, strAwayGoalDetails = awayGoals, strAwayLineupGoalkeeper = awayGk, strAwayLineupDefense = awayDef, strAwayLineupMidfield = awayMid, strAwayLineupForward = awayFor)
            })
            if (favorite.isNullOrEmpty()) {
                emptyData()
            } else {
                showEventFromDb(favorite)
            }
        }
    }

    private fun setupView(){
        rvMatchEvent.layoutManager = LinearLayoutManager(context)
        adapter =  MainAdapter(events){
            startActivity<DetailEventActivity>("key_detail" to it)
        }
        getFromLocal()
        rvMatchEvent.adapter = adapter
    }

    private fun emptyData() {
        rvMatchEvent.invisible()
        progressbarView.invisible()
        txtNodata.visible()
    }


    private fun showEventFromDb(data: List<Events>) {
        rvMatchEvent.visible()
        txtNodata.invisible()
        progressbarView.invisible()
        events.addAll(data)
        adapter.notifyDataSetChanged()
        rvMatchEvent.scrollToPosition(0)
    }

}