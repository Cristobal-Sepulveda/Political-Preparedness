package com.example.android.politicalpreparedness.data

import androidx.lifecycle.LiveData
import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT


interface AppDataSource {

    suspend fun getNextElectionsFromAPIService()
    fun getElectionsFromDatabase(): LiveData<List<ELECTION_DBO>>
}