package com.man.hellosport.ui.league.teams


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson

import com.man.hellosport.R
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.model.league.LeaguesItem
import com.man.hellosport.model.teams.Teams
import com.man.hellosport.ui.adapter.TeamsAdapter
import com.man.hellosport.ui.league.LeagueActivity
import com.man.hellosport.ui.league.teams.mvp.TeamsInterface
import com.man.hellosport.ui.league.teams.mvp.TeamsPresenter
import com.man.hellosport.utils.invisible
import com.man.hellosport.utils.visible
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class TeamsFragment : Fragment(), TeamsInterface {

    lateinit var presenter: TeamsPresenter
    lateinit var data : LeaguesItem
    private var teamsList : MutableList<Teams> = mutableListOf()
    private lateinit var adapter: TeamsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = TeamsPresenter(ApiRepository(), this, Gson())
        data = (activity as LeagueActivity).getLeagueItem()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView(){
        rvTeamsLeague.layoutManager = LinearLayoutManager(context)
        adapter = TeamsAdapter(teamsList){
           // startActivity<EventsActivity>("key_detail" to it)
        }
        presenter.getTeamsLeague(data.idLeague)
        rvTeamsLeague.adapter = adapter
    }


    override fun showLoading() {
        progressbarTeams.visible()
        rvTeamsLeague.invisible()
    }

    override fun hideLoading() {
        progressbarTeams.invisible()
        rvTeamsLeague.visible()
    }

    override fun showTeamList(data: List<Teams>) {
        teamsList.addAll(data)
        adapter.notifyDataSetChanged()
        rvTeamsLeague.scrollToPosition(0)
    }

}
