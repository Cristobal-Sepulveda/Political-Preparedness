package com.example.android.politicalpreparedness.data.data_objects.dbo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.data_objects.dto.DIVISION

@Entity
data class ELECTION_DBO(
    val name: String,
    val electionDay: String,
    @Embedded(prefix = "division_")val division: DIVISION,
    @PrimaryKey val id: Int
)

fun List<ELECTION_DBO>.asDomainModel(): List<ELECTION_DOMAIN_OBJECT> {
    return map {
        ELECTION_DOMAIN_OBJECT (
            name = it.name,
            electionDay = it.electionDay,
            division = it.division,
            id = it.id
        )
    }
}