package com.mnatsakanyan.android.nearbyvenues.data.venue.datasource.query

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal abstract class PlacesQueryBuilder {

    fun build(): Map<String, String> {
        val queryParams = hashMapOf("v" to dateFormat.format(Date()))
        putQueryParams(queryParams)
        return queryParams
    }

    protected abstract fun putQueryParams(queryParams: MutableMap<String, String>)

    companion object {
        private val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.ROOT)
    }
}
