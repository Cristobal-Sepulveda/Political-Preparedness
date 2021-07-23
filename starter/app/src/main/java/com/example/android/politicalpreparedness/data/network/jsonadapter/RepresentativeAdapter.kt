package com.example.android.politicalpreparedness.data.network.jsonadapter

import com.example.android.politicalpreparedness.data.data_objects.dto.DIVISION
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class RepresentativeAdapter {
    @FromJson
    fun divisionFromJson (ocdDivisionId: String): DIVISION {
        val countryDelimiter = "country:"
        val stateDelimiter = "state:"
        val country = ocdDivisionId.substringAfter(countryDelimiter,"")
                .substringBefore("/")
        val state = ocdDivisionId.substringAfter(stateDelimiter,"")
                .substringBefore("/")
        return DIVISION(ocdDivisionId, country, state)
    }

    @ToJson
    fun divisionToJson (division: DIVISION): String {
        return division.id
    }
}