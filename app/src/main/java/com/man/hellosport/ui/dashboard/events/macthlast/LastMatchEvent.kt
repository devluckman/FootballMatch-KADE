package com.man.hellosport.ui.dashboard.events.macthlast


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.man.hellosport.R
import com.man.hellosport.model.event.Events
import com.man.hellosport.ui.adapter.MainAdapter
import com.man.hellosport.ui.base.BaseFragment
import com.man.hellosport.ui.detail.events.EventsActivity
import com.man.hellosport.ui.dashboard.events.mvp.ScheduleInterface
import com.man.hellosport.ui.dashboard.events.mvp.SchedulePresenter
import com.man.hellosport.utils.invisible
import com.man.hellosport.utils.visible
import kotlinx.android.synthetic.main.events_view.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class LastMatchEvent : BaseFragment<SchedulePresenter, ScheduleInterface>(),
    ScheduleInterface {

    private var eventLast : MutableList<Events> = mutableListOf()

    override fun createPresenter(): SchedulePresenter =
        SchedulePresenter(apiRepository)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.events_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView(){
        rvMatchEvent.layoutManager = LinearLayoutManager(context)
        adapter = MainAdapter(eventLast){
            startActivity<EventsActivity>("key_detail" to it)
        }
        presenter.getLastMatch(4335.toString())
        rvMatchEvent.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
    }


    override fun showLoading() {
        progressbarView.visible()
        rvMatchEvent.invisible()
    }

    override fun hideLoading() {
        progressbarView?.invisible()
        rvMatchEvent?.visible()
    }

    override fun showEventList(data: List<Events>) {
        eventLast.addAll(data)
        adapter.notifyDataSetChanged()
        rvMatchEvent?.scrollToPosition(0)
    }
}
