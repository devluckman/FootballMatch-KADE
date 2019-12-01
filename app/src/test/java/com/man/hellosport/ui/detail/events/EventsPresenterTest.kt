package com.man.hellosport.ui.detail.events

import com.google.gson.Gson
import com.man.hellosport.TestContextProvider
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.model.teams.TeamRespone
import com.man.hellosport.model.teams.Teams
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class EventsPresenterTest {

    @Mock
    private lateinit var view: EventsContract

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var presenter: EventsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventsPresenter(view, apiRepository, gson, TestContextProvider())
    }


    // Unit Test Detail Match
    @Test
    fun getTeamDetail() {
        val teamsHome: MutableList<Teams> = mutableListOf()
        val teamsAway: MutableList<Teams> = mutableListOf()
        val responseHome = TeamRespone(teamsHome)
        val responseAway = TeamRespone(teamsAway)
        val idHomeTeam = "133601"
        val idAwayTeam = "134777"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamRespone::class.java
                )
            ).thenReturn(responseHome)

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamRespone::class.java
                )
            ).thenReturn(responseAway)

            presenter.getTeamDetail(idHomeTeam, idAwayTeam)
            Mockito.verify(view).showTeamdetails(teamsHome, teamsAway)
        }

    }
}