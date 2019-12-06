package com.man.hellosport.ui.dashboard.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.man.hellosport.R
import com.man.hellosport.model.teams.Teams
import com.man.hellosport.ui.adapter.TeamsAdapter
import com.man.hellosport.ui.detail.teams.TeamsActivity
import com.man.hellosport.ui.dashboard.favorite.mvp.TeamFavoritePresenter
import com.man.hellosport.ui.dashboard.favorite.mvp.TeamFavoriteView
import com.man.hellosport.utils.invisible
import com.man.hellosport.utils.visible
import kotlinx.android.synthetic.main.fragment_teams_favorite.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamsFragment : Fragment(),
    TeamFavoriteView {


    private var teamsListFavorite : MutableList<Teams> = mutableListOf()
    private lateinit var adapter: TeamsAdapter
    private lateinit var presenter : TeamFavoritePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter =
            TeamFavoritePresenter(this, context!!)
        setupView()
    }

    override fun onResume() {
        super.onResume()
        presenter.getFromLocalData()
    }

    private fun setupView(){
        rvTeamsLeagueFavorite.layoutManager = LinearLayoutManager(context)
        adapter = TeamsAdapter(teamsListFavorite){
            startActivity<TeamsActivity>("key_detail" to it)
        }
        presenter.getFromLocalData()
        rvTeamsLeagueFavorite.adapter = adapter
    }

    override fun showLoading() {
        progressbarTeamsFavorite.visible()
        rvTeamsLeagueFavorite.invisible()
    }

    override fun hideLoading() {
        progressbarTeamsFavorite.invisible()
        rvTeamsLeagueFavorite.visible()
    }

    override fun emptyData() {
        rvTeamsLeagueFavorite.invisible()
        progressbarTeamsFavorite.invisible()
        txtNodataFavorite.visible()
    }

    override fun showTeamListFavorite(data: List<Teams>) {
        teamsListFavorite.clear()
        teamsListFavorite.addAll(data)
        adapter.notifyDataSetChanged()
        rvTeamsLeagueFavorite.scrollToPosition(0)
    }

}
