package com.man.hellosport.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.man.hellosport.R
import com.man.hellosport.data.local.Favorite
import com.man.hellosport.data.local.database
import com.man.hellosport.model.Events
import com.man.hellosport.model.Teams
import com.man.hellosport.utils.invisible
import com.man.hellosport.utils.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_event.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast

class DetailEventActivity : AppCompatActivity(), DetailEventContract {

    private val presenter = DetailEventPresenter(this)
    private var data: Any? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)
        supportActionBar!!.hide()
        data = intent.getParcelableExtra("key_detail")
        data?.let {
            when (it) {
                is Events -> {
                    presenter.getTeamDetail(it.idHomeTeam, it.idAwayTeam)
                    loadData(it)
                }
                is Favorite -> loadDataLocal(it)
            }
        }


    }


    private fun addToFavorite() {
        this.database.use {
            insert(
                Favorite.TABLE_FAVORITE,
                Favorite.ID to idEvent.text.toString(),
                Favorite.DATE_EVENT to dateScheduleTv.text.toString(),
                Favorite.HOME_TEAM_NAME to tvNameHome.text.toString(),
                Favorite.HOME_TEAM_SCORE to tvScoreHome.text.toString(),
                Favorite.HOME_TEAM_GOALS to tvGkHome.text.toString(),
                Favorite.HOME_TEAM_GK to tvGkHome.text.toString(),
                Favorite.HOME_TEAM_DEF to tvDefHome.text.toString(),
                Favorite.HOME_TEAM_MID to tvMidHome.text.toString(),
                Favorite.HOME_TEAM_FORW to tvForHome.text.toString(),
                Favorite.HOME_TEAM_BADGE to urlHome.text.toString(),
                Favorite.AWAY_TEAM_NAME to tvNameAway.text.toString(),
                Favorite.AWAY_TEAM_SCORE to tvScoreAway.text.toString(),
                Favorite.AWAY_TEAM_GOALS to tvGoalsAway.text.toString(),
                Favorite.AWAY_TEAM_GK to tvGkAway.text.toString(),
                Favorite.AWAY_TEAM_DEF to tvDefAway.text.toString(),
                Favorite.AWAY_TEAM_MID to tvMidAway.text.toString(),
                Favorite.AWAY_TEAM_FORW to tvForAway,
                Favorite.AWAY_TEAM_BADGE to urlAway.text.toString()
            )
        }
        toast("Berhasil di Tambahkan")
    }

    private fun removeDatabase(){
        this.database.use {
            delete(Favorite.TABLE_FAVORITE,
                "(${Favorite.ID} = {id})",
                "id" to idEvent.text.toString())
            toast("Berhasil di Hapus")
        }
    }



    private fun loadDataLocal(events: Favorite) {
        idEvent.text = events.idEvent

        dateScheduleTv.text = events.dateEvent
        tvScoreHome.text = events.intHomeScore
        tvScoreAway.text = events.intAwayScore

        tvNameHome.text = events.strHomeTeam
        tvNameAway.text = events.strAwayTeam

        tvGoalsHome.text = events.strHomeGoalDetails
        tvGoalsAway.text = events.strAwayGoalDetails

        tvGkHome.text = events.strHomeLineupGoalkeeper
        tvGkAway.text = events.strAwayLineupGoalkeeper

        tvDefHome.text = events.strHomeLineupDefense
        tvDefAway.text = events.strAwayLineupDefense

        tvMidHome.text = events.strHomeLineupMidfield
        tvMidAway.text = events.strAwayLineupMidfield

        tvForHome.text = events.strHomeLineupForward
        tvForAway.text = events.strAwayLineupForward

        Picasso.get()
            .load(events.strTeamBadgeHome)
            .into(ImgHome)

        Picasso.get()
            .load(events.strTeamBadgeAway)
            .into(ImgAway)
        urlHome.text = events.strTeamBadgeHome
        urlAway.text = events.strTeamBadgeAway
    }


    private fun loadData(events: Events) {
        dateScheduleTv.text = events.dateEvent
        tvScoreHome.text = events.intHomeScore
        tvScoreAway.text = events.intAwayScore

        tvNameHome.text = events.strHomeTeam
        tvNameAway.text = events.strAwayTeam

        tvGoalsHome.text = events.strHomeGoalDetails
        tvGoalsAway.text = events.strAwayGoalDetails

        tvGkHome.text = events.strHomeLineupGoalkeeper
        tvGkAway.text = events.strAwayLineupGoalkeeper

        tvDefHome.text = events.strHomeLineupDefense
        tvDefAway.text = events.strAwayLineupDefense

        tvMidHome.text = events.strHomeLineupMidfield
        tvMidAway.text = events.strAwayLineupMidfield

        tvForHome.text = events.strHomeLineupForward
        tvForAway.text = events.strAwayLineupForward
    }

    override fun showLoading() {
        progressbarD.visible()
    }

    override fun hideLoading() {
        progressbarD.invisible()
    }

    override fun showTeamdetails(dataHomeTeam: List<Teams>, dataAwayTeam: List<Teams>) {
        Picasso.get()
            .load(dataHomeTeam[0].strTeamBadge)
            .into(ImgHome)

        Picasso.get()
            .load(dataAwayTeam[0].strTeamBadge)
            .into(ImgAway)
    }

}
