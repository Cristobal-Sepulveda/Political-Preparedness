package com.example.android.politicalpreparedness.data.network.jsonadapter

import com.example.android.politicalpreparedness.data.data_objects.dto.DIVISION
import com.example.android.politicalpreparedness.data.data_objects.dto.DIVISION_DTO
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class ElectionAdapter {
    @FromJson
    fun divisionFromJson (ocdDivisionId: String): DIVISION_DTO {
        val countryDelimiter = "country:"
        val stateDelimiter = "state:"
        val country = ocdDivisionId.substringAfter(countryDelimiter,"")
                .substringBefore("/")
        val state = ocdDivisionId.substringAfter(stateDelimiter,"")
                .substringBefore("/")
        return DIVISION_DTO(ocdDivisionId, country, state)
    }

    @ToJson
    fun divisionToJson (division: DIVISION): String {
        return division.id
    }
}