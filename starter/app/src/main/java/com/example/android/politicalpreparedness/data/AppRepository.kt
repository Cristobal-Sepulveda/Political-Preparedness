package com.example.android.politicalpreparedness.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO
import com.example.android.politicalpreparedness.data.data_objects.dto.asDataBaseModel
import com.example.android.politicalpreparedness.data.database.ElectionDao
import com.example.android.politicalpreparedness.data.network.CivicApi
import com.example.android.politicalpreparedness.util.EspressoIdlingResource.wrapEspressoIdlingResource
import kotlinx.coroutines.*

class AppRepository(private val electionDao: ElectionDao,
                    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): AppDataSource {

    var a = 1
    override suspend fun getNextElectionsFromAPIService(){
        wrapEspressoIdlingResource {
            withContext(Dispatchers.IO) {
                try {
                    val electionApiResponse = CivicApi.RETROFIT_SERVICE_CIVIC_API.getElections(
                        "AIzaSyBNGDAG_qIAaCtZGcDftVXgI75-CwcWg64"
                    ).elections

                    for (element in electionApiResponse) {
                        electionDao.insertElection(element.asDataBaseModel(element))
                        Log.i("INSERT", "$element")
                    }
                    Log.i("INSERT", "ELECCIONES INSERTADAS")
                } catch (e: Exception) {
                    Log.i("ERROR", "ERROR: $e")
                }
            }
        }
    }

    override fun getElectionsFromDatabase(): LiveData<List<ELECTION_DBO>> {
        return electionDao.getAllElections()
    }


}

