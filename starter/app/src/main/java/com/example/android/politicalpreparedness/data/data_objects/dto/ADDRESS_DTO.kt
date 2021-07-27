package com.example.android.politicalpreparedness.data.data_objects.dto

import com.example.android.politicalpreparedness.data.data_objects.domain_object.ADDRESS_DOMAIN_OBJECT

data class ADDRESS_DTO(
    val line1: String,
    val line2: String? = null,
    val city: String,
    val state: String,
    val zip: String
)

fun ADDRESS_DTO.asDomainModel(addressDto: ADDRESS_DTO): ADDRESS_DOMAIN_OBJECT{
    return ADDRESS_DOMAIN_OBJECT(
        line1 = addressDto.line1,
        line2 = addressDto.line2,
        city = addressDto.city,
        state = addressDto.state,
        zip = addressDto.zip
    )
}