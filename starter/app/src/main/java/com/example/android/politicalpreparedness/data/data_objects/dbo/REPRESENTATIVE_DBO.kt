package com.example.android.politicalpreparedness.data.data_objects.dbo

import androidx.room.PrimaryKey
import java.util.*


data class REPRESENTATIVE_DBO(
    val name: String,
    val party: String,
    val name_of_publicCharge: String,
    val cityInCharge: String,
    val stateOfCharge: String,
    val website: String,
    val facebook: String,
    val twitter: String,
    @PrimaryKey val id: String/* = UUID.randomUUID().toString()*/
)



