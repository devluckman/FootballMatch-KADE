package com.man.hellosport.ui.detail.events

import com.man.hellosport.model.teams.Teams

interface EventsContract {
    fun showTeamdetails(dataHomeTeam: List<Teams>, dataAwayTeam: List<Teams>)
}