package com.example.android.politicalpreparedness.data.network.jsonadapter

import com.example.android.politicalpreparedness.data.data_objects.domain_object.DIVISION_DOMAIN_OBJECT
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class RepresentativeAdapter {
    @FromJson
    fun divisionFromJson (ocdDivisionId: String): DIVISION_DOMAIN_OBJECT {
        val countryDelimiter = "country:"
        val stateDelimiter = "state:"
        val country = ocdDivisionId.substringAfter(countryDelimiter,"")
                .substringBefore("/")
        val state = ocdDivisionId.substringAfter(stateDelimiter,"")
                .substringBefore("/")
        return DIVISION_DOMAIN_OBJECT(ocdDivisionId, country, state)
    }

    @ToJson
    fun divisionToJson (division: DIVISION_DOMAIN_OBJECT): String {
        return division.id
    }
}