package com.example.android.politicalpreparedness.data.data_objects.dto

import com.example.android.politicalpreparedness.data.data_objects.domain_object.DIVISION_DOMAIN_OBJECT

data class DIVISION_DTO(
    val id: String,
    val country: String,
    val state: String
)

fun DIVISION_DTO.asDomainModel(): DIVISION_DOMAIN_OBJECT {
    return DIVISION_DOMAIN_OBJECT(
        id = this.id,
        country = this.country,
        state = this.state
    )
}