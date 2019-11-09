package com.man.hellosport.ui.menu.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.man.hellosport.R
import com.man.hellosport.ui.schedule.lastmatch.LastMatchEvent
import com.man.hellosport.ui.schedule.nextmatch.NextMatchEvent
import kotlinx.android.synthetic.main.fragment_match_list.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private  fun init() {
        val bundle = Bundle()
        bundle.putBoolean("fav", false)
        viewpager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> LastMatchEvent.newInstance(bundle)
                    else -> NextMatchEvent.newInstance(bundle)
                }
            }
            override fun getItemCount(): Int = 2
        }
        TabLayoutMediator(tabs, viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> "Last Match"
                else -> "Next Match"
            }
        }.attach()
    }
}