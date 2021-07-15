package com.example.android.politicalpreparedness.data.data_objects.dto

import androidx.room.*
import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO
import com.squareup.moshi.*
import java.util.*

data class ELECTION_DTO(
        val name: String,
        val electionDay: Date,
        val division: Division,
        val id: Int
)

fun ELECTION_DTO.asDataBaseModel(electionDto: ELECTION_DTO): ELECTION_DBO {
        return ELECTION_DBO(
                name = electionDto.name,
                electionDay = electionDto.electionDay,
                division = electionDto.division,
                id = electionDto.id
        )
}