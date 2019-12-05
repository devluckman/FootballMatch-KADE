package com.man.hellosport.ui.dashboard.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.man.hellosport.R
import com.man.hellosport.model.event.Events
import com.man.hellosport.ui.adapter.EventsAdapter
import com.man.hellosport.ui.dashboard.favorite.mvp.FavoriteContract
import com.man.hellosport.ui.dashboard.favorite.mvp.FavoritePresenter
import com.man.hellosport.ui.detail.events.EventsActivity
import com.man.hellosport.utils.invisible
import com.man.hellosport.utils.visible
import kotlinx.android.synthetic.main.events_view.*
import org.jetbrains.anko.support.v4.startActivity


class FavoriteFragment : Fragment(), FavoriteContract {


    private lateinit var adapter: EventsAdapter
    private var events : MutableList<Events> = mutableListOf()
    private lateinit var presenter : FavoritePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.events_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = FavoritePresenter(this, context!!)
        setupView()
    }

    override fun onResume() {
        super.onResume()
        presenter.getFromLocalData()
    }


    private fun setupView(){
        rvMatchEvent.layoutManager = LinearLayoutManager(context)
        adapter =  EventsAdapter(events){
            startActivity<EventsActivity>("key_detail" to it)
        }
        presenter.getFromLocalData()
        rvMatchEvent.adapter = adapter
    }

    override fun emptyData() {
        rvMatchEvent.invisible()
        progressbarView.invisible()
        txtNodata.visible()
    }

    override fun showLoading() {
        progressbarView.visible()
        rvMatchEvent.invisible()
    }

    override fun hideLoading() {
        progressbarView.invisible()
        rvMatchEvent.visible()
    }

    override fun showEventList(data: List<Events>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
        rvMatchEvent.scrollToPosition(0)
    }


}