package com.man.hellosport.ui.dashboard.events.mvp

import com.google.gson.Gson
import com.man.hellosport.TestContextProvider
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.data.network.TheSportdbApi
import com.man.hellosport.model.event.Events
import com.man.hellosport.model.event.EventsRespone
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SchedulePresenterTest {

    @Mock
    private lateinit var view: ScheduleInterface

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var presenter: SchedulePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SchedulePresenter(apiRepository, view, gson, TestContextProvider())
    }

    @Test
    fun getNextMatch() {
        val events: MutableList<Events> = mutableListOf()
        val response = EventsRespone(events)
        val league = "4435"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    EventsRespone::class.java
                )
            ).thenReturn(response)

            presenter.getNextMatch(league)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showEventList(events)
        }
    }

    @Test
    fun getLastMatch() {
        val events: MutableList<Events> = mutableListOf()
        val response = EventsRespone(events)
        val league = "4435"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    EventsRespone::class.java
                )
            ).thenReturn(response)

            presenter.getLastMatch(league)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showEventList(events)
        }
    }
}