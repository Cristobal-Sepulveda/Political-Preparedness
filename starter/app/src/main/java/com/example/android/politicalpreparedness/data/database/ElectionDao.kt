package com.example.android.politicalpreparedness.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO

@Dao
interface ElectionDao {

    //TODO: Add insert query
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElection(vararg electionDbo: ELECTION_DBO)

    //TODO: Add select all election query
    @Query("SELECT * FROM ELECTION_DBO")
    fun getAllElections(): List<ELECTION_DBO>

    //TODO: Add select single election query
    @Query("SELECT * FROM ELECTION_DBO where id = :id")
    suspend fun getElection(id: String): ELECTION_DBO

    //TODO: Add delete query
    @Query("DELETE FROM ELECTION_DBO")
    suspend fun deleteRepresentatives()

    //TODO: Add clear query
    @Query("DELETE FROM ELECTION_DBO WHERE id = :id")
    suspend fun unfollowElection(id: String)

}