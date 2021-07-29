package com.example.android.politicalpreparedness.data.data_objects.domain_object

import android.os.Parcelable
import com.example.android.politicalpreparedness.data.data_objects.dto.Office
import com.example.android.politicalpreparedness.data.data_objects.dto.Official
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class REPRESENTATIVE_DOMAIN_OBJECT(
    val name: String,
    val party: String,
    val name_of_publicCharge: String,
    val cityInCharge: String,
    val stateOfCharge: String,
    val website: String,
    val facebook: String,
    val twitter: String,
    val id: String
): Parcelable