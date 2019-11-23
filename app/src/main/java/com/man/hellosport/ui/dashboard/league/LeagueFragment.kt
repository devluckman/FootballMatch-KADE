package com.man.hellosport.ui.dashboard.league


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson

import com.man.hellosport.R
import com.man.hellosport.model.league.LeagueResponse
import com.man.hellosport.model.league.LeaguesItem
import com.man.hellosport.ui.adapter.LeagueAdapter
import com.man.hellosport.ui.detail.league.LeagueActivity
import com.man.hellosport.utils.LeagueUtils
import kotlinx.android.synthetic.main.fragment_league.*
import org.jetbrains.anko.support.v4.startActivity

class LeagueFragment : Fragment() {


    private lateinit var adapter: LeagueAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }


    private fun setupView(){
        val response = Gson().fromJson(LeagueUtils.getJson(context!!), LeagueResponse::class.java)
        rvLeague.layoutManager = LinearLayoutManager(context)
        adapter =  LeagueAdapter(response.leagues!!){
            startActivity<LeagueActivity>("league" to it)
        }
        rvLeague.adapter = adapter
    }

}
