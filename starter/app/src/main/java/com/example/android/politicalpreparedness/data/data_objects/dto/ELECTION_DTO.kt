package com.example.android.politicalpreparedness.data.data_objects.dto

import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class ELECTION_DTO_Container(var elections: ArrayList<ELECTION_DTO>)

@JsonClass(generateAdapter = true)

data class ELECTION_DTO(
        val id: Int,
        val name: String,
        val electionDay: Date,
        @Json(name = "ocdDivisionId")val division: DIVISION_DTO
)

fun ELECTION_DTO.asDomainModel(electionDto: ELECTION_DTO): ELECTION_DOMAIN_OBJECT{
        return ELECTION_DOMAIN_OBJECT(
                id = electionDto.id,
                name = electionDto.name,
                electionDay = electionDto.electionDay.toString(),
                division = electionDto.division.asDomainModel()
        )
}
fun ELECTION_DTO_Container.asDomainModel(): List<ELECTION_DOMAIN_OBJECT> {
        return elections.map{
                ELECTION_DOMAIN_OBJECT(
                        id = it.id,
                        name = it.name,
                        electionDay = it.electionDay.toString(),
                        division = it.division.asDomainModel()
                )
        }
}
fun ELECTION_DTO_Container.asDataBaseModel(): Array<ELECTION_DBO> {
        return elections.map {
                ELECTION_DBO(
                        id = it.id,
                        name = it.name,
                        electionDay = it.electionDay.toString(),
                        division = it.division.asDomainModel()
                )
        }.toTypedArray()
}