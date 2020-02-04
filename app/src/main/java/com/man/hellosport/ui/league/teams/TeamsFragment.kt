package com.man.hellosport.ui.league.teams


import android.app.SearchManager
import android.content.Context.SEARCH_SERVICE
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.man.hellosport.R
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.model.league.LeaguesItem
import com.man.hellosport.model.teams.Teams
import com.man.hellosport.ui.adapter.TeamsAdapter
import com.man.hellosport.ui.detail.teams.TeamsActivity
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

    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

    override fun onResume() {
        super.onResume()
        presenter.getTeamsLeague(data.idLeague)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchManager = (activity as LeagueActivity).getSystemService(SEARCH_SERVICE) as SearchManager

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }

        if (searchView != null) {
            searchView!!.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))

            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isNotEmpty())
                        presenter.getSearchTeams(newText)
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {

                    return true
                }
            }
            searchView!!.setOnQueryTextListener(queryTextListener)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search ->
                // Not implemented here
                return false
            else -> {
            }
        }
        searchView!!.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }


    private fun setupView(){
        rvTeamsLeague.layoutManager = LinearLayoutManager(context)
        adapter = TeamsAdapter(teamsList){
            startActivity<TeamsActivity>("key_detail" to it)
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
        teamsList.clear()
        teamsList.addAll(data)
        adapter.notifyDataSetChanged()
        rvTeamsLeague.scrollToPosition(0)
    }

}
