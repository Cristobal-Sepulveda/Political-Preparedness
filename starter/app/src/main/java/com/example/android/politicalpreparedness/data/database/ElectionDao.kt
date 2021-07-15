package com.example.android.politicalpreparedness.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.politicalpreparedness.data.data_objects.dbo.REPRESENTATIVE_DBO

@Dao
interface ElectionDao {

    //TODO: Add insert query
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElection(representativeDbo: REPRESENTATIVE_DBO)

    //TODO: Add select all election query
    @Query("SELECT * FROM REPRESENTATIVE_DBO")
    suspend fun getAllElections(): List<REPRESENTATIVE_DBO>

    //TODO: Add select single election query
    @Query("SELECT * FROM REPRESENTATIVE_DBO where id = :id")
    suspend fun getElection(id: String): REPRESENTATIVE_DBO

    //TODO: Add delete query
    @Query("DELETE FROM REPRESENTATIVE_DBO")
    suspend fun deleteRepresentatives()

    //TODO: Add clear query
    @Query("DELETE FROM REPRESENTATIVE_DBO WHERE id = :id")
    suspend fun unfollowElection(id: String)

}