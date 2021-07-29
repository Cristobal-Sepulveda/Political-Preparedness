package com.example.android.politicalpreparedness.data.data_objects.dto

import com.example.android.politicalpreparedness.data.data_objects.domain_object.DIVISION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.data_objects.domain_object.REPRESENTATIVE_DOMAIN_OBJECT
import com.squareup.moshi.Json

data class Office (
    val name: String,
    @Json(name="divisionId") val division: DIVISION_DOMAIN_OBJECT,
    @Json(name="officialIndices") val officials: List<Int>
) {
    fun getRepresentatives(officials: List<Official>): List<Representative> {
        return this.officials.map { index ->
            Representative(officials[index], this)
        }
    }
}
