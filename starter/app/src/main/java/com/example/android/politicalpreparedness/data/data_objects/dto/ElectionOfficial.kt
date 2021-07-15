package com.example.android.politicalpreparedness.data.data_objects.dto

import com.squareup.moshi.Json

data class ElectionOfficial(
    val name: String,
    val title: String,
    @Json(name="officePhoneNumber") val phone: String,
    @Json(name="faxNumber") val fax: String,
    val emailAddress: String
)