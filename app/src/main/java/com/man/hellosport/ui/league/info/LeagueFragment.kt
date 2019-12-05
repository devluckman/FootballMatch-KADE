package com.man.hellosport.ui.league.info


import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.man.hellosport.R
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.model.league.LeaguesItem
import com.man.hellosport.ui.league.LeagueActivity
import com.man.hellosport.ui.league.info.mvp.LeagueContract
import com.man.hellosport.ui.league.info.mvp.LeaguePresenter
import com.man.hellosport.ui.league.info.slider.BannerAdapter
import com.man.hellosport.utils.invisible
import com.man.hellosport.utils.visible
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_league_detail.*

/**
 * A simple [Fragment] subclass.
 */
class LeagueFragment : Fragment(),  LeagueContract, View.OnClickListener {

    private lateinit var data: LeaguesItem
    private val presenter = LeaguePresenter(this, ApiRepository(), Gson())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = (activity as LeagueActivity).getLeagueItem()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data.let {
            presenter.getLeagueDetail(data.idLeague)
        }

        icnFacebook.setOnClickListener(this)
        icnTwitter.setOnClickListener(this)
        icnYoutube.setOnClickListener(this)
        icnWeb.setOnClickListener(this)
    }

    override fun showLoad() {
        cardView.invisible()
        line2.invisible()
        line3.invisible()
        progressLeague.visible()
    }

    override fun hideLoad() {
        cardView.visible()
        line2.visible()
        line3.visible()
        progressLeague.invisible()
    }


    override fun showLeaguedetails(leagues: List<LeaguesItem>) {
        data = leagues[0]
        txtCountry.text = data.strCountry
        txtDate.text = data.dateFirstEvent
        txtDesc.text = data.strDescriptionEN

        setupView(leagues[0])
    }

    private fun setupView(league : LeaguesItem){
        val list = listOf(league.strFanart1!!,league.strFanart2!!, league.strFanart3!!, league.strFanart4!! )
        setupSilder(list)
    }

    override fun onClick(v: View?) {
        when(v){
            icnFacebook -> startActivity(openUrls(data.strFacebook!!))
            icnTwitter -> startActivity(openUrls(data.strTwitter!!))
            icnYoutube -> startActivity(openUrls(data.strYoutube!!))
            icnWeb -> startActivity(openUrls(data.strWebsite!!))
        }
    }

    private fun openUrls(url : String) : Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse("http://$url"))
    }

    private fun setupSilder(list: List<String>){
        val adapter = BannerAdapter(list)
        sliderView.sliderAdapter = adapter

        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE)
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION)
        sliderView.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        sliderView.indicatorSelectedColor = Color.WHITE
        sliderView.indicatorUnselectedColor = Color.GRAY
        sliderView.startAutoCycle()
        sliderView.setOnIndicatorClickListener { position ->
            sliderView.currentPagePosition = position
        }
    }

}
