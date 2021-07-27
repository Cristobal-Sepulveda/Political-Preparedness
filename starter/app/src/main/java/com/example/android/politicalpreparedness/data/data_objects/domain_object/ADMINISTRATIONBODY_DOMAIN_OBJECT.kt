package com.example.android.politicalpreparedness.data.data_objects.domain_object


data class ADMINISTRATIONBODY_DOMAIN_OBJECT(
    val name: String? = null,
    val electionInfoUrl: String? = null,
    val votingLocationFinderUrl: String? = null,
    val ballotInfoUrl: String? = null,
    val correspondenceAddress: ADDRESS_DOMAIN_OBJECT? = null
)
