package com.man.hellosport.ui.dashboard.events

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.SearchEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.man.hellosport.R
import com.man.hellosport.model.eventbus.EventLeague
import com.man.hellosport.ui.dashboard.events.macthlast.LastMatchEvent
import com.man.hellosport.ui.dashboard.events.matchnext.NextMatchEvent
import kotlinx.android.synthetic.main.fragment_events.*
import org.greenrobot.eventbus.EventBus

class EventsFragment : Fragment() {
    private var bundle = Bundle()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupView()

    }

    private fun setupView(){
        val listLeague = listOf("Select League","English Premier League","German Bundesliga","Italian Serie A","French Ligue 1","Spanish La Liga")
        val spinnerArrayAdapter = object : ArrayAdapter<String>(context!!, R.layout.spinner_item, listLeague) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }
            override fun getDropDownView(position: Int, convertView: View?,
                                         parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.BLACK)
                }
                return view
            }
        }
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item)

        spinner.adapter = spinnerArrayAdapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItemText = parent.getItemAtPosition(position) as String
                if (position > 0) {
                    // Notify the selected item text
                    bundle.putString("key",selectedItemText.id())
                    EventBus.getDefault().post(EventLeague(selectedItemText.id()))
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }

    fun String.id() : String{
        var id = ""
        when{
            this == "English Premier League" -> id = 4328.toString()
            this == "German Bundesliga" -> id =  4331.toString()
            this == "Italian Serie A" -> id =  4332.toString()
            this == "French Ligue 1" -> id =  4334.toString()
            this == "Spanish La Liga" -> id =  4335.toString()
        }
        return id
    }

    private  fun init() {
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