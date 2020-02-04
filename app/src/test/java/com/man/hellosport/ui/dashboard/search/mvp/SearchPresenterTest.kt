package com.man.hellosport.ui.dashboard.search.mvp

import com.google.gson.Gson
import com.man.hellosport.TestContextProvider
import com.man.hellosport.data.network.ApiRepository
import com.man.hellosport.model.event.Events
import com.man.hellosport.model.search.SearchResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchPresenterTest {


    @Mock
    private lateinit var view: SearchContract

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var presenter: SearchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchPresenter(view, apiRepository, gson, TestContextProvider())
    }


    // Unit Test Search Match
    @Test
    fun getSearchMatch() {
        val events: MutableList<Events> = mutableListOf()
        val response = SearchResponse(events)
        val query = "Barcelona"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    SearchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getSearchMatch(query)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).hideLoading()
            Mockito.verify(view).showResult(events)
        }

    }
}