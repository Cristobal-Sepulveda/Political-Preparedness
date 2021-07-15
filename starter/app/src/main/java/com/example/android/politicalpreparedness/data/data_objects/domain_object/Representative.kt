package com.example.android.politicalpreparedness.data.data_objects.domain_object

import com.example.android.politicalpreparedness.data.data_objects.dto.Office
import com.example.android.politicalpreparedness.data.data_objects.dto.Official

data class Representative (
    val official: Official,
    val office: Office
)