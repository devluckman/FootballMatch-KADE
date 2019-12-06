package com.man.hellosport.ui.league.standing


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.man.hellosport.R
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.model.league.LeaguesItem
import com.man.hellosport.model.standing.TableItem
import com.man.hellosport.ui.adapter.StandingAdapter
import com.man.hellosport.ui.league.LeagueActivity
import com.man.hellosport.ui.league.standing.mvp.StandingPresenter
import com.man.hellosport.ui.league.standing.mvp.StandingView
import com.man.hellosport.utils.invisible
import com.man.hellosport.utils.visible
import kotlinx.android.synthetic.main.fragment_standing.*

/**
 * A simple [Fragment] subclass.
 */
class StandingFragment : Fragment(), StandingView {

    private lateinit var presenter : StandingPresenter
    lateinit var data : LeaguesItem
    private var tableListItem : MutableList<TableItem> = mutableListOf()

    private lateinit var adapter: StandingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = StandingPresenter(this, ApiRepository(), Gson())
        data = (activity as LeagueActivity).getLeagueItem()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onResume() {
        super.onResume()
        presenter.getStandingLeague(data.idLeague)
    }

    private fun setupView(){
        rvStanding.layoutManager = LinearLayoutManager(context)
        adapter = StandingAdapter(tableListItem)
        presenter.getStandingLeague(data.idLeague)
        rvStanding.adapter = adapter
    }

    override fun showLoadingStanding() {
        rvStanding.invisible()
        progressbarStanding.visible()
    }

    override fun hideLoadingStanding() {
        rvStanding.visible()
        progressbarStanding.invisible()
    }

    override fun showTableList(tableList: List<TableItem>) {
        tableListItem.clear()
        tableListItem.addAll(tableList)
        adapter.notifyDataSetChanged()
        rvStanding.scrollToPosition(0)
    }


}
