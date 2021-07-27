package com.example.android.politicalpreparedness.data

import android.util.Log
import com.example.android.politicalpreparedness.data.data_objects.dto.asDataBaseModel
import com.example.android.politicalpreparedness.data.database.ElectionDao
import com.example.android.politicalpreparedness.data.network.CivicApi
import com.example.android.politicalpreparedness.util.EspressoIdlingResource.wrapEspressoIdlingResource
import com.example.android.politicalpreparedness.util.parseElectionsToContainer
import kotlinx.coroutines.*

class AppRepository(private val electionDao: ElectionDao,
                    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): AppDataSource {

    override suspend fun gettingAndSavingInDBNextElections_fromAPIService() {
        wrapEspressoIdlingResource {
            withContext(ioDispatcher) {
                try {
                    val electionApiResponse = CivicApi.RETROFIT_SERVICE_CIVIC_API.getElections(
                        "AIzaSyBNGDAG_qIAaCtZGcDftVXgI75-CwcWg64")
                    val electionApiResponseElectionList = electionApiResponse.elections
                    Log.i("INSERT", "ELECCIONES FROM API: ${electionApiResponseElectionList.size}")
                    val electionsParsed = parseElectionsToContainer(electionApiResponseElectionList)
                    electionDao.insertElection(*electionsParsed.asDataBaseModel())
                } catch (e: Exception) {
                    Log.i("ERROR", "ERROR: $e")
                }
            }
        }
    }

}

