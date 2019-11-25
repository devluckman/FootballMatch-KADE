package com.man.hellosport.ui.dashboard.events.matchnext


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.man.hellosport.R
import com.man.hellosport.model.event.Events
import com.man.hellosport.model.eventbus.EventLeague
import com.man.hellosport.ui.adapter.EventsAdapter
import com.man.hellosport.ui.base.BaseFragment
import com.man.hellosport.ui.dashboard.events.mvp.ScheduleInterface
import com.man.hellosport.ui.dashboard.events.mvp.SchedulePresenter
import com.man.hellosport.ui.detail.events.EventsActivity
import com.man.hellosport.utils.invisible
import com.man.hellosport.utils.visible
import kotlinx.android.synthetic.main.events_view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class NextMatchEvent : BaseFragment<SchedulePresenter, ScheduleInterface>(),
    ScheduleInterface {

    private var eventNext : MutableList<Events> = mutableListOf()
    private var idLeague = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idLeague = it.getString("key",4335.toString())
        }
    }


    override fun createPresenter(): SchedulePresenter =
        SchedulePresenter(apiRepository, this, gson)


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
        adapter = EventsAdapter(eventNext){
            startActivity<EventsActivity>("key_detail" to it)
        }

        presenter.getNextMatch(idLeague)

        rvMatchEvent.adapter = adapter
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
        eventNext.addAll(data)
        adapter.notifyDataSetChanged()
        rvMatchEvent?.scrollToPosition(0)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        EventBus.getDefault().register(this)
    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUpdateRequest(event: EventLeague) {
        showLoading()
        eventNext.clear()
        presenter.getNextMatch(event.id)
    }

    companion object{
        fun newInstance(bundle : Bundle) : NextMatchEvent{
            val fragment = NextMatchEvent()
            fragment.arguments = bundle
            return fragment
        }
    }
}
