package com.mnatsakanyan.android.nearbyvenues.data.venue.model

import com.mnatsakanyan.android.nearbyvenues.domain.venue.model.Venue

internal data class ResponseWrapper(
        val results: List<Venue>?
)
