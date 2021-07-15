package com.example.android.politicalpreparedness.data.data_objects.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ElectionResponse(
        val kind: String,
        val elections: List<ELECTION_DTO>
)