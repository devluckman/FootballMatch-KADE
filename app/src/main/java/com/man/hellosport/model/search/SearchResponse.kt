package com.man.hellosport.model.search

import com.man.hellosport.model.event.Events

data class SearchResponse(
    val event: List<Events>
)