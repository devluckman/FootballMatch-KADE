package com.man.hellosport.ui.detail.league.mvp

import com.google.gson.Gson
import com.man.hellosport.TestContextProvider
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.model.league.LeagueResponse
import com.man.hellosport.model.league.LeaguesItem
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LeaguePresenterTest {


    @Mock
    private lateinit var view: LeagueContract

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var presenter: LeaguePresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LeaguePresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun getLeagueDetail() {
        val league : MutableList<LeaguesItem> = mutableListOf()
        val response = LeagueResponse(league)
        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    LeagueResponse::class.java
                )
            ).thenReturn(response)

            presenter.getLeagueDetail(idLeague)
            Mockito.verify(view).showLeaguedetails(league)
        }
    }
}