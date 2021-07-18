package com.example.android.politicalpreparedness.data

import com.example.android.politicalpreparedness.data.database.ElectionDao
import com.example.android.politicalpreparedness.data.network.CivicApi
import com.example.android.politicalpreparedness.data.network.CivicApiService
import kotlinx.coroutines.*
import retrofit2.await

class AppRepository(private val electionDao: ElectionDao,
                    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): AppDataSource
{
    override suspend fun getNextElectionsFromAPIService(){
        withContext(ioDispatcher){
            val electionsResponse = CivicApi.RETROFIT_SERVICE_CIVIC_API.getElections(
                "AIzaSyBNGDAG_qIAaCtZGcDftVXgI75-CwcWg64"
            )
            println(electionsResponse)
        }
    }

}
