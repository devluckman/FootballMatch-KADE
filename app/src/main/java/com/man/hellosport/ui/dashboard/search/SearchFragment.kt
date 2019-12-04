package com.man.hellosport.ui.dashboard.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.man.hellosport.R
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.model.event.Events
import com.man.hellosport.ui.adapter.EventsAdapter
import com.man.hellosport.ui.dashboard.search.mvp.SearchContract
import com.man.hellosport.ui.dashboard.search.mvp.SearchPresenter
import com.man.hellosport.ui.detail.events.EventsActivity
import com.man.hellosport.utils.invisible
import com.man.hellosport.utils.visible
import kotlinx.android.synthetic.main.fragment_search.*
import org.jetbrains.anko.support.v4.startActivity


class SearchFragment : Fragment() , SearchContract{

    private val presenter = SearchPresenter(this, ApiRepository(), Gson())
    private var events : MutableList<Events> = mutableListOf()
    private lateinit var adapter: EventsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

        textInputSearch.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (textInputSearch.text.toString().isNotEmpty()){
                        events.clear()
                        adapter.notifyDataSetChanged()
                        presenter.getSearchMatch(textInputSearch.text.toString())
                    }
                    return true
                }
                return false
            }
        })
    }

    private fun setupView(){
//        textInputSearch.isFocusableInTouchMode = true
//        textInputSearch.requestFocus()
        rvSearch.layoutManager = LinearLayoutManager(context)
        adapter = EventsAdapter(events){
            startActivity<EventsActivity>("key_detail" to it)
        }
        rvSearch.adapter = adapter
    }

    override fun hideLoading() {
        progressbarSearch.invisible()
        rvSearch.visible()
    }

    override fun showLoading() {
        progressbarSearch.visible()
        rvSearch.invisible()
    }

    override fun showResult(list: List<Events>) {
        events.addAll(list)
        adapter.notifyDataSetChanged()
    }


}
