package com.example.android.politicalpreparedness.data.data_objects.dto

import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class ELECTION_DTO_Container(var asteroids: ArrayList<ELECTION_DTO>)

@JsonClass(generateAdapter = true)
data class ELECTION_DTO(
        val id: Int,
        val name: String,
        val electionDay: Date,
        @Json(name = "ocdDivisionId")val division: String
)

fun List<ELECTION_DTO>.asDataBaseModel(): Array<ELECTION_DBO> {
        return this.map {
                ELECTION_DBO(
                        id = it.id,
                        name = it.name,
                        electionDay = it.electionDay.toString(),
                        division = it.division
                )
        }.toTypedArray()
}