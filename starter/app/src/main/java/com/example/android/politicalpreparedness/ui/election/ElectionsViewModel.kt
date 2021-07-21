package com.example.android.politicalpreparedness.ui.election

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.android.politicalpreparedness.base.BaseViewModel
import com.example.android.politicalpreparedness.data.AppDataSource
import com.example.android.politicalpreparedness.data.AppRepository
import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO
import com.example.android.politicalpreparedness.data.data_objects.dbo.asDomainModel
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.database.ElectionDatabase
import com.example.android.politicalpreparedness.data.database.getDatabase
import kotlinx.coroutines.launch

//TODO: Construct ViewModel and provide election datasource
class ElectionsViewModel(val app: Application, val dataSource: AppRepository) : BaseViewModel(app) {

    /******************************     AUX S        ***********************************/
    private val database = getDatabase(app)
    private val _reloadFragment = MutableLiveData<Boolean>()
    val reloadFragment : LiveData<Boolean>
        get() = _reloadFragment

    /**********************************************************************************/

    init{
        viewModelScope.launch{

            if(dataSource.gettingAndSavingInDB_NextElectionsFromAPIService()) {
                val electionsInDatabase = dataSource.getElectionsFromDatabase()
                Log.i("INSERT", "$electionsInDatabase")
                Log.i("INSERT", "ASDASDASDASDASDASD")
            }else{
                _reloadFragment.value = true
            }
        }
    }
    fun reloadFragmentDone() {
        _reloadFragment.value = false
    }

    suspend fun gettingElectionsFromDB(): List<ELECTION_DOMAIN_OBJECT>{
        return dataSource.getElectionsFromDatabase()
    }



}


//TODO: Create live data val for saved elections

    //TODO: Create val and functions to populate live data for upcoming elections from the API and saved elections from local database

    //TODO: Create functions to navigate to saved or upcoming election voter info
