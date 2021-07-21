package com.example.android.politicalpreparedness.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO
import com.example.android.politicalpreparedness.data.data_objects.dbo.asDomainModel
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.data_objects.dto.ELECTION_DTO
import com.example.android.politicalpreparedness.data.data_objects.dto.asDataBaseModel
import com.example.android.politicalpreparedness.data.database.ElectionDao
import com.example.android.politicalpreparedness.data.network.CivicApi
import com.example.android.politicalpreparedness.util.EspressoIdlingResource.wrapEspressoIdlingResource
import com.example.android.politicalpreparedness.util.parseElectionsJsonResult
import kotlinx.coroutines.*
import org.json.JSONObject

class AppRepository(private val electionDao: ElectionDao,
                    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): AppDataSource {

    override suspend fun gettingAndSavingInDB_NextElectionsFromAPIService(): Boolean {
        wrapEspressoIdlingResource {
            withContext(Dispatchers.IO) {
                try {
                    val electionApiResponse = CivicApi.RETROFIT_SERVICE_CIVIC_API.getElections(
                        "AIzaSyBNGDAG_qIAaCtZGcDftVXgI75-CwcWg64")
                    val electionApiResponseElectionList = electionApiResponse.elections
                    val electionDTOtoElectionDBO = electionApiResponseElectionList.asDataBaseModel() as List<*>
                    electionDao.insertElection(*electionDTOtoElectionDBO)
                    Log.i("INSERT", "ELECCIONES FROM API: ${electionApiResponse.elections}")
                    Log.i("INSERT", "$electionApiResponseElectionList")
                    Log.i("INSERT", "ELECCIONES TO DB: $electionDTOtoElectionDBO")
                    Log.i("INSERT", "${electionDao.getAllElections()}")
                    return@withContext true
                } catch (e: Exception) {
                    Log.i("ERROR", "ERROR: $e")
                    return@withContext false
                }
            }
            return false
        }
    }

    override suspend fun getElectionsFromDatabase(): List<ELECTION_DOMAIN_OBJECT> {
        return electionDao.getAllElections().asDomainModel()
    }
}

