package com.man.hellosport.ui.detail.events

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.man.hellosport.R
import com.man.hellosport.data.local.FavoriteMatch
import com.man.hellosport.data.local.database
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.model.event.Events
import com.man.hellosport.model.teams.Teams
import com.man.hellosport.utils.FormatDate.AppLog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_event.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class EventsActivity : AppCompatActivity(),
    EventsContract {

    private val presenter = EventsPresenter(this, ApiRepository(), Gson())
    private var favorite = false
    private lateinit var data: Events
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event)
        supportActionBar!!.hide()
        data = intent.getParcelableExtra("key_detail")!!
        data.let {
            presenter.getTeamDetail(it.idHomeTeam, it.idAwayTeam)
            loadData(it)
        }
        checkFavorite()

        imgFavorite.setOnClickListener {
            favorite = !favorite
            setImgFavorite()
            saveState()
        }

        imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun saveState(){
        if (favorite){
            setData()
            addToFavorite()
        }else{
            removeDatabase()
        }
    }

    private fun checkFavorite(){
        try {
            database.use {
                val result = select(FavoriteMatch.TABLE_FAVORITE)
                    .whereArgs("(${FavoriteMatch.ID_EVENT} = {id})", "id" to data.idEvent!!)
                val eventFavorite = result.parseList(classParser<Events>())
                favorite = eventFavorite.isNotEmpty()
                setImgFavorite()

            }
        }catch (e : SQLiteConstraintException){

        }
    }

    private fun setData(){
        data.intAwayScore = setValue(data.intAwayScore)
        data.strAwayGoalDetails = setValue(data.strAwayGoalDetails)
        data.strAwayLineupDefense = setValue(data.strAwayLineupDefense)
        data.strAwayLineupForward = setValue(data.strAwayLineupForward)
        data.strAwayLineupGoalkeeper = setValue(data.strAwayLineupGoalkeeper)
        data.strAwayLineupMidfield = setValue(data.strAwayLineupMidfield)
        data.intHomeScore = setValue(data.intHomeScore)
        data.strHomeGoalDetails = setValue(data.strHomeGoalDetails)
        data.strHomeLineupDefense = setValue(data.strHomeLineupDefense)
        data.strHomeLineupForward = setValue(data.strHomeLineupForward)
        data.strHomeLineupGoalkeeper = setValue(data.strHomeLineupGoalkeeper)
        data.strHomeLineupMidfield = setValue(data.strHomeLineupMidfield)
    }

    private fun setValue(data : String?) : String{
        return when{
            data == null || data.isEmpty() -> "-"
            else -> data
        }
    }

    private fun setImgFavorite(){
        if (favorite){
            imgFavorite.setBackgroundResource(R.drawable.ic_favorite_24dp)
        }else{
            imgFavorite.setBackgroundResource(R.drawable.ic_unfavorite_24dp)
        }
    }

    private fun addToFavorite() {
        try{
          database.use {
                insert(
                    FavoriteMatch.TABLE_FAVORITE,
                    FavoriteMatch.ID_EVENT to data.idEvent,
                    FavoriteMatch.DATE_EVENT to data.dateEvent,

                    FavoriteMatch.HOME_TEAM_ID to data.idHomeTeam,
                    FavoriteMatch.HOME_TEAM_NAME to data.strHomeTeam,
                    FavoriteMatch.HOME_TEAM_SCORE to data.intHomeScore,
                    FavoriteMatch.HOME_TEAM_GOALS to data.strHomeGoalDetails,
                    FavoriteMatch.HOME_TEAM_GK to data.strHomeLineupGoalkeeper,
                    FavoriteMatch.HOME_TEAM_DEF to data.strHomeLineupDefense,
                    FavoriteMatch.HOME_TEAM_MID to data.strHomeLineupMidfield,
                    FavoriteMatch.HOME_TEAM_FORW to data.strHomeLineupForward,

                    FavoriteMatch.AWAY_TEAM_ID to data.idAwayTeam,
                    FavoriteMatch.AWAY_TEAM_NAME to data.strAwayTeam,
                    FavoriteMatch.AWAY_TEAM_SCORE to data.intAwayScore,
                    FavoriteMatch.AWAY_TEAM_GOALS to data.strAwayGoalDetails,
                    FavoriteMatch.AWAY_TEAM_GK to data.strAwayLineupGoalkeeper,
                    FavoriteMatch.AWAY_TEAM_DEF to data.strAwayLineupDefense,
                    FavoriteMatch.AWAY_TEAM_MID to data.strAwayLineupMidfield,
                    FavoriteMatch.AWAY_TEAM_FORW to data.strAwayLineupForward
                )
            }
            toast("Data Berhasil di Simpan")
        }catch (e : SQLiteConstraintException){
            toast("Gagal Menyimpan data ${e.localizedMessage}")
        }
    }

    private fun removeDatabase(){
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE,
                    "(${FavoriteMatch.ID_EVENT} = {id})",
                    "id" to data.idEvent!!)
                toast("Data Berhasil di Hapus")
            }

        }catch (e : SQLiteConstraintException){
            toast("Gagal Menghapus data ${e.localizedMessage}")
        }
    }

    private fun loadData(events: Events) {
        AppLog("Check data $events")
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

    override fun showTeamdetails(dataHomeTeam: List<Teams>, dataAwayTeam: List<Teams>) {
        Picasso.get()
            .load(dataHomeTeam[0].strTeamBadge)
            .into(ImgHome)

        Picasso.get()
            .load(dataAwayTeam[0].strTeamBadge)
            .into(ImgAway)
    }

}
