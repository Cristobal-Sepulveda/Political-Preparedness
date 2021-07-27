package com.example.android.politicalpreparedness.data.data_objects.dto

import androidx.lifecycle.Transformations.map
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ADMINISTRATIONBODY_DOMAIN_OBJECT
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Mapper.Companion.map

@JsonClass(generateAdapter = true)
data class ADMINISTRATIONBODY_DTO (
        val name: String,
        val electionInfoUrl: String,
        val votingLocationFinderUrl: String,
        val ballotInfoUrl: String,
        val correspondenceAddress: ADDRESS_DTO
)

fun ADMINISTRATIONBODY_DTO.asDomainModel(administrationbodyDomainObject: ADMINISTRATIONBODY_DTO):
        ADMINISTRATIONBODY_DOMAIN_OBJECT{
        return ADMINISTRATIONBODY_DOMAIN_OBJECT(
                name = administrationbodyDomainObject.name,
                electionInfoUrl = administrationbodyDomainObject.electionInfoUrl,
                votingLocationFinderUrl = administrationbodyDomainObject.votingLocationFinderUrl,
                ballotInfoUrl = administrationbodyDomainObject.ballotInfoUrl,
                correspondenceAddress = administrationbodyDomainObject.correspondenceAddress?.let {
                        administrationbodyDomainObject.correspondenceAddress.asDomainModel(
                                it
                        )
                }
        )
}