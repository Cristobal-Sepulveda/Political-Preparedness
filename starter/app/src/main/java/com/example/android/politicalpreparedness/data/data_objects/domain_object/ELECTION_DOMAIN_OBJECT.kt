package com.example.android.politicalpreparedness.data.data_objects.domain_object

import android.os.Parcelable
import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ELECTION_DOMAIN_OBJECT(
    val name: String,
    val electionDay: String,
    val division: DIVISION_DOMAIN_OBJECT,
    val id: Int
): Parcelable

fun ELECTION_DOMAIN_OBJECT.asDatabaseModel(electionDomainObject: ELECTION_DOMAIN_OBJECT):
        ELECTION_DBO {
    return ELECTION_DBO(
        name = electionDomainObject.name,
        electionDay = electionDomainObject.electionDay,
        division = electionDomainObject.division,
        id = electionDomainObject.id
    )
}