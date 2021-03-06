package com.example.android.politicalpreparedness.data.data_objects.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class VoterInfoResponse (
    val election: ELECTION_DTO,
    val pollingLocations: String? = null, //TODO: Future Use
    val contests: String? = null, //TODO: Future Use
    val state: List<State>,
    val electionElectionOfficials: List<ElectionOfficial>? = null
)