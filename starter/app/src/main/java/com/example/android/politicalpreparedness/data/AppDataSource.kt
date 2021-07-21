package com.example.android.politicalpreparedness.data

import androidx.lifecycle.LiveData
import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.ui.election.ElectionsViewModel
import org.koin.android.ext.android.inject


interface AppDataSource {
    suspend fun gettingAndSavingInDB_NextElectionsFromAPIService():Boolean
    suspend fun getElectionsFromDatabase(): List<ELECTION_DOMAIN_OBJECT>

}