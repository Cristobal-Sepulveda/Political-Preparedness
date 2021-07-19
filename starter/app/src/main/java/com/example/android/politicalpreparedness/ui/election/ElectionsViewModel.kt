package com.example.android.politicalpreparedness.ui.election

import android.app.Application
import androidx.lifecycle.*
import com.example.android.politicalpreparedness.base.BaseViewModel
import com.example.android.politicalpreparedness.data.AppDataSource
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.database.ElectionDatabase
import kotlinx.coroutines.launch

//TODO: Construct ViewModel and provide election datasource
class ElectionsViewModel(val app: Application, val dataSource: AppDataSource) : BaseViewModel(app) {

    //TODO: Create live data val for upcoming elections
    /**
     * This fix the app freezing issue and toggle between Asteroid list
     * smoothly, MutableLiveData give freeze problems.
     */
    val electionListToDisplayInTheScreen:
            MediatorLiveData<List<ELECTION_DOMAIN_OBJECT>> = MediatorLiveData()

    private val _nextElectionList = MutableLiveData<List<ELECTION_DOMAIN_OBJECT>>()
    val nextElectionList : LiveData<List<ELECTION_DOMAIN_OBJECT>>
        get() = _nextElectionList





    init{
        viewModelScope.launch{
            _nextElectionList= dataSource.getNextElectionsFromAPIService()
            if(nextElectionList == null) {

            }else {
                electionListToDisplayInTheScreen.addSource(nextElectionList)
            }
        }
    }
}



    //TODO: Create live data val for saved elections

    //TODO: Create val and functions to populate live data for upcoming elections from the API and saved elections from local database

    //TODO: Create functions to navigate to saved or upcoming election voter info

}