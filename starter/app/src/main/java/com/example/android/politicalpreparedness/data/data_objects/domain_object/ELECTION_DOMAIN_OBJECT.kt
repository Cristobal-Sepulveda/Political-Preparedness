package com.example.android.politicalpreparedness.data.data_objects.domain_object

import com.example.android.politicalpreparedness.data.data_objects.dto.Division
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ELECTION_DOMAIN_OBJECT(
    val name: String,
    val electionDay: Date,
    val division: Division,
    val id: Int
)