package com.example.android.politicalpreparedness.data.data_objects.dto

data class RepresentativeResponse(
        val officials: List<Official>,
        val offices: List<Office>
)
val RepresentativeResponse.representatives
        get() = offices.flatMap { it.getRepresentatives(officials) }

data class Representative (
        val official: Official,
        val office: Office
)