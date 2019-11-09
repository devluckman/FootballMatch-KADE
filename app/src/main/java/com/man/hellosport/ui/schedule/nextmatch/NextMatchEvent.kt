package com.man.hellosport.ui.schedule.nextmatch


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
import kotlinx.android.synthetic.main.nextmatch_layout.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class NextMatchEvent : Fragment(), NextMatchContract {

    private val presenter= NextMatchPresenter(this)
    private lateinit var adapter: MainAdapter
    private lateinit var adapterFavorite: FavoriteAdapter
    private var events : MutableList<Events> = mutableListOf()
    private var eventFavorite : MutableList<Favorite> = mutableListOf()
    private var favorite = false

    companion object{
        fun newInstance(bundle : Bundle) : NextMatchEvent{
            val fragment = NextMatchEvent()
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
        return inflater.inflate(R.layout.nextmatch_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView(){
        RecNextMatch.layoutManager = LinearLayoutManager(context)
        when{
            favorite ->{
                adapterFavorite = FavoriteAdapter(eventFavorite){
                    startActivity<DetailEventActivity>("key_detail" to it)
                }
                presenter.getFromLocal(context!!)
                RecNextMatch.adapter = adapterFavorite
            }
            else -> {
                adapter = MainAdapter(events){
                    startActivity<DetailEventActivity>("key_detail" to it)
                }
                presenter.getFromApi(4335.toString())
                RecNextMatch.adapter = adapter
            }
        }
    }


    override fun showLoading() {
        progressbarNext.visible()
        RecNextMatch.invisible()
    }

    override fun hideLoading() {
        progressbarNext.invisible()
        RecNextMatch.visible()
    }

    override fun showEventList(data: List<Events>) {
        events.addAll(data)
        adapter.notifyDataSetChanged()
        RecNextMatch.scrollToPosition(0)
    }

    override fun showEventFromDb(data: List<Favorite>) {
        eventFavorite.addAll(data)
        adapterFavorite.notifyDataSetChanged()
        RecNextMatch.scrollToPosition(0)
    }


}
