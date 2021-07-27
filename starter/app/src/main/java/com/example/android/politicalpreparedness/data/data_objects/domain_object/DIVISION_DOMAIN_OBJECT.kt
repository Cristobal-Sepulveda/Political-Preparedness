package com.example.android.politicalpreparedness.data.data_objects.domain_object

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DIVISION_DOMAIN_OBJECT(
        val id: String,
        val country: String,
        val state: String
) : Parcelable


