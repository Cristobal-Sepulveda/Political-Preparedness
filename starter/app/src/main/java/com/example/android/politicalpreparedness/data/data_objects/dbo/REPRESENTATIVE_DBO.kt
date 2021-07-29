package com.example.android.politicalpreparedness.data.data_objects.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.data_objects.domain_object.REPRESENTATIVE_DOMAIN_OBJECT
import java.util.*

@Entity
data class REPRESENTATIVE_DBO(
    val name: String,
    val party: String,
    val name_of_publicCharge: String,
    val cityInCharge: String,
    val stateOfCharge: String,
    val website: String,
    val facebook: String,
    val twitter: String,
    @PrimaryKey val id: String
)

fun REPRESENTATIVE_DBO.asDomainModel(representativeDbo: REPRESENTATIVE_DBO): REPRESENTATIVE_DOMAIN_OBJECT {
    return REPRESENTATIVE_DOMAIN_OBJECT (
        name = representativeDbo.name,
        party = representativeDbo.party,
        name_of_publicCharge = representativeDbo.name_of_publicCharge,
        cityInCharge = representativeDbo.cityInCharge,
        stateOfCharge = representativeDbo.stateOfCharge,
        website = representativeDbo.website,
        facebook = representativeDbo.facebook,
        twitter = representativeDbo.twitter,
        id = representativeDbo.id
    )
}



