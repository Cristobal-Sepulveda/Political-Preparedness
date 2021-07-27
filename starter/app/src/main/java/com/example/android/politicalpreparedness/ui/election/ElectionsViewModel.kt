package com.example.android.politicalpreparedness.ui.election

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.android.politicalpreparedness.base.BaseViewModel
import com.example.android.politicalpreparedness.data.AppDataSource
import com.example.android.politicalpreparedness.data.data_objects.dbo.asDomainModel
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.data_objects.dto.asDataBaseModel
import com.example.android.politicalpreparedness.data.data_objects.dto.asDomainModel
import com.example.android.politicalpreparedness.data.database.getDatabase
import com.example.android.politicalpreparedness.data.network.CivicApi
import com.example.android.politicalpreparedness.util.NavigationCommand
import com.example.android.politicalpreparedness.util.parseElectionsToContainer
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//TODO: Construct ViewModel and provide election datasource
class ElectionsViewModel(val app: Application, val dataSource: AppDataSource) : BaseViewModel(app) {

    val database = getDatabase(app)
    private val _reloadFragment = MutableLiveData<Boolean>()
    val reloadFragment : LiveData<Boolean>
        get() = _reloadFragment

    //TODO: Create val and functions to populate live data for upcoming elections
    // from the API and saved elections from local database
    private val _domainElectionsFromApiInScreen = MutableLiveData<List<ELECTION_DOMAIN_OBJECT>>()
    val domainElectionsFromApiInScreen: LiveData<List<ELECTION_DOMAIN_OBJECT>>
        get()= _domainElectionsFromApiInScreen

    //TODO: Create live data val for saved elections
    /** GETTING DATA FROM DB*/
    val electionListFromDatabase = Transformations.map(database.electionDao.getAllElections()) {
        it.asDomainModel()
    }
    val domainElectionsSavedInScreen:
            MediatorLiveData<List<ELECTION_DOMAIN_OBJECT>> = MediatorLiveData()

    init{
        viewModelScope.launch{
            try {
                /** DRAWING DATA FROM DB*/
                domainElectionsSavedInScreen.addSource(electionListFromDatabase){
                    domainElectionsSavedInScreen.value = it
                }

                /** GETTING DATA FROM API*/
                val electionApiResponse = CivicApi.RETROFIT_SERVICE_CIVIC_API.getElections(
                    "AIzaSyBNGDAG_qIAaCtZGcDftVXgI75-CwcWg64")
                val electionApiResponseElectionList = electionApiResponse.elections
                Log.i("INSERT", "ELECCIONES FROM API: ${electionApiResponseElectionList.size}")
                val electionsParsed = parseElectionsToContainer(electionApiResponseElectionList)
                _domainElectionsFromApiInScreen.value = electionsParsed.asDomainModel()

            } catch (e: Exception) {
                Log.i("ERROR", "ERROR: $e")
            }
        }
    }

    //TODO: Create functions to navigate to saved or upcoming election voter info
    fun navigateToVoterInfo(electionDomainObject: ELECTION_DOMAIN_OBJECT){
        navigationCommand.value = NavigationCommand
            .To(ElectionsFragmentDirections
                .actionElectionsFragmentToVoterInfoFragment(electionDomainObject.id,
                    electionDomainObject.division))
    }

    fun reloadFragmentDone() {
        _reloadFragment.value = false
    }
}

