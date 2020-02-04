package com.man.hellosport.ui.dashboard.events.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.ui.adapter.EventsAdapter
import com.man.hellosport.ui.dashboard.events.mvp.ScheduleInterface
import com.man.hellosport.ui.dashboard.events.mvp.SchedulePresenter

abstract class BaseFragment< P : SchedulePresenter, V : ScheduleInterface> : Fragment() {
    protected lateinit var presenter: SchedulePresenter
    protected lateinit var adapter: EventsAdapter
    protected var gson  = Gson()
    protected abstract fun createPresenter() : P
    protected var apiRepository = ApiRepository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

}