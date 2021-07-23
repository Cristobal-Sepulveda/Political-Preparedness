package com.example.android.politicalpreparedness.data.data_objects.domain_object

import android.os.Parcelable
import com.example.android.politicalpreparedness.data.data_objects.dto.DIVISION
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ELECTION_DOMAIN_OBJECT(
    val name: String,
    val electionDay: String,
    val division: DIVISION,
    val id: Int
): Parcelable