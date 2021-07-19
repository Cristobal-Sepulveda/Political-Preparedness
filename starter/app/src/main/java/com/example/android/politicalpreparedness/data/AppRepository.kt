package com.example.android.politicalpreparedness.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO
import com.example.android.politicalpreparedness.data.data_objects.dbo.asDomainModel
import com.example.android.politicalpreparedness.data.data_objects.dto.ElectionResponse
import com.example.android.politicalpreparedness.data.data_objects.dto.asDataBaseModel
import com.example.android.politicalpreparedness.data.database.ElectionDao
import com.example.android.politicalpreparedness.data.network.CivicApi
import com.example.android.politicalpreparedness.util.EspressoIdlingResource.wrapEspressoIdlingResource
import kotlinx.coroutines.*

class AppRepository(private val electionDao: ElectionDao,
                    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): AppDataSource {

    override suspend fun getNextElectionsFromAPIService(): Boolean {
        wrapEspressoIdlingResource {
            try {
                val electionApiResponse = CivicApi.RETROFIT_SERVICE_CIVIC_API.getElections(
                    "AIzaSyBNGDAG_qIAaCtZGcDftVXgI75-CwcWg64"
                ).elections
                for (element in electionApiResponse) {
                    electionDao.insertElection(element.asDataBaseModel(element))
                }

            } catch (e: Exception) {
                println("Error: $e")
            }
        }
    }


        val electionListFromDatabase = Transformations.map(electionDao.getAllElections()){
            it.asDomainModel()
        }

    }

}



}
