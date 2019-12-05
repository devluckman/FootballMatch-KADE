package com.man.hellosport.ui.detail.teams

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.man.hellosport.R
import com.man.hellosport.data.local.FavoriteTeam
import com.man.hellosport.data.local.database
import com.man.hellosport.model.teams.Teams
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_teams.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class TeamsActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var data: Teams
    private var favorite = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)
        supportActionBar!!.hide()
        data = intent.getParcelableExtra("key_detail")!!

        data.let {
            Picasso.get().load(data.strTeamBadge).placeholder(R.drawable.img_barca).into(logoTeams)
            txtTeams.text = data.strTeam
            txtCountry.text = data.strCountry
            txtStadium.text = data.strStadium
            txtDesc.text = data.strDescriptionEN
        }

        icnFacebook.setOnClickListener(this)
        icnInstagram.setOnClickListener(this)
        icnTwitter.setOnClickListener(this)
        icnWeb.setOnClickListener(this)
        icnYoutube.setOnClickListener(this)
        backPressed.setOnClickListener(this)
        imgFavorite.setOnClickListener(this)

        checkFavorite()

    }

    override fun onClick(v: View?) {
        when(v){
            icnInstagram -> startActivity(openUrls(data.strInstagram!!))
            icnFacebook -> startActivity(openUrls(data.strFacebook!!))
            icnTwitter -> startActivity(openUrls(data.strTwitter!!))
            icnYoutube -> startActivity(openUrls(data.strYoutube!!))
            icnWeb -> startActivity(openUrls(data.strWebsite!!))
            backPressed -> onBackPressed()
            imgFavorite -> {
                favorite = !favorite
                setImgFavorite()
                saveState()
            }
        }
    }

    private fun openUrls(url : String) : Intent {
        return Intent(Intent.ACTION_VIEW, Uri.parse("http://$url"))
    }

    private fun addToFavorite() {
        try{
            database.use {
                insert(FavoriteTeam.TABLE_TEAMS,
                    FavoriteTeam.ID_TEAMS to data.idTeam,
                    FavoriteTeam.TEAMS_NAME to data.strTeam,
                    FavoriteTeam.TEAMS_BADGE to data.strTeamBadge,
                    FavoriteTeam.TEAMS_COUNTRY to data.strCountry,
                    FavoriteTeam.TEAMS_FB to data.strFacebook,
                    FavoriteTeam.TEAMS_YT to data.strYoutube,
                    FavoriteTeam.TEAMS_TW to data.strTwitter,
                    FavoriteTeam.TEAMS_RRS to data.strWebsite,
                    FavoriteTeam.TEAMS_IG to data.strInstagram,
                    FavoriteTeam.TEAMS_DESC to data.strDescriptionEN,
                    FavoriteTeam.TEAMS_STADIUM to data.strStadium
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
                delete(FavoriteTeam.TABLE_TEAMS,
                    "(${FavoriteTeam.ID_TEAMS} = {id})",
                    "id" to data.idTeam!!)
                toast("Data Berhasil di Hapus")
            }

        }catch (e : SQLiteConstraintException){
            toast("Gagal Menghapus data ${e.localizedMessage}")
        }
    }

    private fun checkFavorite(){
        try {
            database.use {
                val result = select(FavoriteTeam.TABLE_TEAMS)
                    .whereArgs("(${FavoriteTeam.ID_TEAMS} = {id})", "id" to data.idTeam!!)
                val teamsFavorite = result.parseList(classParser<Teams>())
                favorite = teamsFavorite.isNotEmpty()
                setImgFavorite()

            }
        }catch (e : SQLiteConstraintException){

        }
    }

    private fun setImgFavorite(){
        if (favorite){
            imgFavorite.setBackgroundResource(R.drawable.ic_favorite_24dp)
        }else{
            imgFavorite.setBackgroundResource(R.drawable.ic_unfavorite_24dp)
        }
    }

    private fun saveState(){
        if (favorite){
            addToFavorite()
        }else{
            removeDatabase()
        }
    }

}
