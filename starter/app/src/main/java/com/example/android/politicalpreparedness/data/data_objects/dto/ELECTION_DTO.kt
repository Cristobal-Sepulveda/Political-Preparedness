package com.example.android.politicalpreparedness.data.data_objects.dto

import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class ELECTION_DTO(
        val id: Int,
        val name: String,
        val electionDay: Date,
        @Json(name = "ocdDivisionId")val division: String
)

fun ELECTION_DTO.asDataBaseModel(electionDto: ELECTION_DTO): ELECTION_DBO {
        return ELECTION_DBO(
                id = electionDto.id,
                name = electionDto.name,
                electionDay = electionDto.electionDay.toString(),
                division = electionDto.division
        )
}