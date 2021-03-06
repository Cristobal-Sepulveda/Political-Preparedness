package com.example.android.politicalpreparedness.data.data_objects.dto

import com.example.android.politicalpreparedness.data.data_objects.domain_object.ADDRESS_DOMAIN_OBJECT

data class Official (
    val name: String,
    val address: List<ADDRESS_DOMAIN_OBJECT>? = null,
    val party: String? = null,
    val phones: List<String>? = null,
    val urls: List<String>? = null,
    val photoUrl: String? = null,
    val channels: List<Channel>? = null
)