package com.man.hellosport.ui.schedule.lastmatch


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.man.hellosport.R
import com.man.hellosport.data.local.Favorite
import com.man.hellosport.model.Events
import com.man.hellosport.ui.adapter.FavoriteAdapter
import com.man.hellosport.ui.adapter.MainAdapter
import com.man.hellosport.ui.detail.DetailEventActivity
import com.man.hellosport.utils.invisible
import com.man.hellosport.utils.visible
import kotlinx.android.synthetic.main.lastmatch_layout.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class LastMatchEvent : Fragment(), LastMatchContract {

    private val presenter= LastMatchPresenter(this)
    private lateinit var adapter: MainAdapter
    private lateinit var adapterFavorite: FavoriteAdapter
    private var events : MutableList<Events> = mutableListOf()
    private var eventFavorite : MutableList<Favorite> = mutableListOf()
    private var favorite = false

    companion object{
        fun newInstance(bundle : Bundle) : LastMatchEvent{
            val fragment = LastMatchEvent()
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            favorite = it.getBoolean("fav")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.lastmatch_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

    }

    private fun setupView(){
        RecLastMatch.layoutManager = LinearLayoutManager(context)
        when{
            favorite ->{
                adapterFavorite = FavoriteAdapter(eventFavorite){
                    startActivity<DetailEventActivity>("key_detail" to it)
                }
                presenter.getFromLocal(context!!)
                RecLastMatch.adapter = adapterFavorite
            }
            else -> {
                adapter = MainAdapter(events){
                    startActivity<DetailEventActivity>("key_detail" to it)
                }
                presenter.getFromApi(4335.toString())
                RecLastMatch.adapter = adapter
            }
        }
    }

    override fun showLoading() {
        progressbarLast.visible()
        RecLastMatch.invisible()
    }

    override fun hideLoading() {
        progressbarLast.invisible()
        RecLastMatch.visible()
    }

    override fun showEventList(data: List<Events>) {
        events.addAll(data)
        adapter.notifyDataSetChanged()
        RecLastMatch.scrollToPosition(0)
    }

    override fun showEventFromDb(data: List<Favorite>) {
        eventFavorite.addAll(data)
        adapterFavorite.notifyDataSetChanged()
        RecLastMatch.scrollToPosition(0)

    }


}
