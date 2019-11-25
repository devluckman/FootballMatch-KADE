package com.man.hellosport.data.local


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx : Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.createTable(Favorite.TABLE_FAVORITE, true,
            Favorite.ID_EVENT to TEXT + PRIMARY_KEY,
            Favorite.DATE_EVENT to TEXT,

            Favorite.HOME_TEAM_ID to TEXT,
            Favorite.HOME_TEAM_NAME to TEXT,
            Favorite.HOME_TEAM_SCORE to TEXT,
            Favorite.HOME_TEAM_GOALS to TEXT,
            Favorite.HOME_TEAM_GK to TEXT,
            Favorite.HOME_TEAM_DEF to TEXT,
            Favorite.HOME_TEAM_MID to TEXT,
            Favorite.HOME_TEAM_FORW to TEXT,

            Favorite.AWAY_TEAM_ID to TEXT,
            Favorite.AWAY_TEAM_NAME to TEXT,
            Favorite.AWAY_TEAM_SCORE to TEXT,
            Favorite.AWAY_TEAM_GOALS to TEXT,
            Favorite.AWAY_TEAM_GK to TEXT,
            Favorite.AWAY_TEAM_DEF to TEXT,
            Favorite.AWAY_TEAM_MID to TEXT,
            Favorite.AWAY_TEAM_FORW to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, old: Int, new: Int) {
        db!!.dropTable(Favorite.TABLE_FAVORITE, true)
    }

    companion object {
        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            return DatabaseHelper(ctx.applicationContext)
        }
    }
}
// Access property for Context
val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)