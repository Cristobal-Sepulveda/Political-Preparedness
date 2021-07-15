package com.example.android.politicalpreparedness.data.data_objects.dbo

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.politicalpreparedness.data.data_objects.dto.Division
import com.squareup.moshi.Json
import java.util.*

@Entity
data class ELECTION_DBO(
    @ColumnInfo(name = "name")val name: String,
    @ColumnInfo(name = "electionDay")val electionDay: Date,
    @Embedded(prefix = "division_") @Json(name="ocdDivisionId") val division: Division,
    @PrimaryKey val id: Int
)