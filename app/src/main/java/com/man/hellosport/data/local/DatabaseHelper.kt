package com.man.hellosport.data.local


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx : Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1){

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            FavoriteMatch.TABLE_FAVORITE, true,
            FavoriteMatch.ID_EVENT to TEXT + PRIMARY_KEY,
            FavoriteMatch.DATE_EVENT to TEXT,

            FavoriteMatch.HOME_TEAM_ID to TEXT,
            FavoriteMatch.HOME_TEAM_NAME to TEXT,
            FavoriteMatch.HOME_TEAM_SCORE to TEXT,
            FavoriteMatch.HOME_TEAM_GOALS to TEXT,
            FavoriteMatch.HOME_TEAM_GK to TEXT,
            FavoriteMatch.HOME_TEAM_DEF to TEXT,
            FavoriteMatch.HOME_TEAM_MID to TEXT,
            FavoriteMatch.HOME_TEAM_FORW to TEXT,

            FavoriteMatch.AWAY_TEAM_ID to TEXT,
            FavoriteMatch.AWAY_TEAM_NAME to TEXT,
            FavoriteMatch.AWAY_TEAM_SCORE to TEXT,
            FavoriteMatch.AWAY_TEAM_GOALS to TEXT,
            FavoriteMatch.AWAY_TEAM_GK to TEXT,
            FavoriteMatch.AWAY_TEAM_DEF to TEXT,
            FavoriteMatch.AWAY_TEAM_MID to TEXT,
            FavoriteMatch.AWAY_TEAM_FORW to TEXT
        )

        db.createTable(
            FavoriteTeam.TABLE_TEAMS, true,
            FavoriteTeam.ID_TEAMS to TEXT + PRIMARY_KEY,
            FavoriteTeam.TEAMS_NAME to TEXT,
            FavoriteTeam.TEAMS_BADGE to TEXT,
            FavoriteTeam.TEAMS_COUNTRY to TEXT,
            FavoriteTeam.TEAMS_FB to TEXT,
            FavoriteTeam.TEAMS_YT to TEXT,
            FavoriteTeam.TEAMS_TW to TEXT,
            FavoriteTeam.TEAMS_RRS to TEXT,
            FavoriteTeam.TEAMS_IG to TEXT,
            FavoriteTeam.TEAMS_DESC to TEXT,
            FavoriteTeam.TEAMS_STADIUM to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, old: Int, new: Int) {
        db.dropTable(FavoriteMatch.TABLE_FAVORITE, true)
        db.dropTable(FavoriteTeam.TABLE_TEAMS, true)
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