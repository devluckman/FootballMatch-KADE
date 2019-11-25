package com.man.hellosport.ui.detail.league

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.man.hellosport.R
import com.man.hellosport.model.league.LeaguesItem
import com.man.hellosport.ui.detail.league.mvp.LeagueContract
import com.man.hellosport.ui.detail.league.mvp.LeaguePresenter
import com.man.hellosport.ui.detail.league.slider.BannerAdapter
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_league.*


class LeagueActivity : AppCompatActivity() , LeagueContract , View.OnClickListener{

    private lateinit var data: LeaguesItem
    private val presenter = LeaguePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        data = intent.getParcelableExtra("league")!!
        title = data.strLeague
        data.let {
            presenter.getLeagueDetail(data.idLeague)
        }

        icnFacebook.setOnClickListener(this)
        icnTwitter.setOnClickListener(this)
        icnYoutube.setOnClickListener(this)
        icnWeb.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            icnFacebook -> startActivity(openUrls(data.strFacebook!!))
            icnTwitter -> startActivity(openUrls(data.strTwitter!!))
            icnYoutube -> startActivity(openUrls(data.strYoutube!!))
            icnWeb -> startActivity(openUrls(data.strWebsite!!))
        }
    }

    private fun openUrls(url : String) : Intent{
        return Intent(Intent.ACTION_VIEW, Uri.parse("http://$url"))
    }

    private fun setupView(league : LeaguesItem){
        val list = listOf(league.strFanart1!!,league.strFanart2!!, league.strFanart3!!, league.strFanart4!! )
        setupSilder(list)
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

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun showLeaguedetails(leaguesItem: LeaguesItem) {
        data = leaguesItem
        txtCountry.text = data.strCountry
        txtDate.text = data.dateFirstEvent
        txtDesc.text = data.strDescriptionEN

        setupView(leaguesItem)
    }

}
