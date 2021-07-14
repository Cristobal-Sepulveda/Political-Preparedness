package com.example.android.politicalpreparedness.data.data_objects.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.politicalpreparedness.data.data_objects.dbo.REPRESENTATIVE_DBO

@Entity
data class REPRESENTATIVE_DTO(
    val name: String,
    val party: String,
    val name_of_publicCharge: String,
    val cityInCharge: String,
    val stateOfCharge: String,
    val website: String,
    val facebook: String,
    val twitter: String,
    val id: String/* = UUID.randomUUID().toString()*/
)

fun REPRESENTATIVE_DTO.asDataBaseModel(representativeDTO: REPRESENTATIVE_DTO): REPRESENTATIVE_DBO {
    return REPRESENTATIVE_DBO(
        name = representativeDTO.name,
        party =  representativeDTO.party,
        name_of_publicCharge = representativeDTO.name_of_publicCharge,
        cityInCharge = representativeDTO.cityInCharge,
        stateOfCharge = representativeDTO.stateOfCharge,
        website = representativeDTO.website,
        facebook = representativeDTO.facebook,
        twitter= representativeDTO.twitter,
        id = representativeDTO.id
    )
}
