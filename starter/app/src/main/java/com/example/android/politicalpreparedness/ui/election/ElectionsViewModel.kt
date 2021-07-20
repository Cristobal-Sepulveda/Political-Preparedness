package com.example.android.politicalpreparedness.ui.election

import android.app.Application
import androidx.lifecycle.*
import com.example.android.politicalpreparedness.base.BaseViewModel
import com.example.android.politicalpreparedness.data.AppDataSource
import com.example.android.politicalpreparedness.data.AppRepository
import com.example.android.politicalpreparedness.data.data_objects.dbo.asDomainModel
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.database.ElectionDatabase
import kotlinx.coroutines.launch

//TODO: Construct ViewModel and provide election datasource
class ElectionsViewModel(val app: Application, val dataSource: AppDataSource) : BaseViewModel(app) {

    /******************************     AUX S        ***********************************/
    private val _reloadFragment = MutableLiveData<Boolean>()
    val reloadFragment : LiveData<Boolean>
        get() = _reloadFragment


    /**********************************************************************************/

    //TODO: Create live data val for upcoming elections
    /**
     * This fix the app freezing issue and toggle between Asteroid list
     * smoothly, MutableLiveData give freeze problems.
     */
    val electionListToDisplayInTheScreen:
            MediatorLiveData<List<ELECTION_DOMAIN_OBJECT>> = MediatorLiveData()
    private val _listToBindingAdapter = MutableLiveData<Boolean>()
    val listToBindingAdapter : LiveData<Boolean>
        get() = _listToBindingAdapter

    private val _list = MutableLiveData<List<ELECTION_DOMAIN_OBJECT>>()
    val list : LiveData<List<ELECTION_DOMAIN_OBJECT>>
        get() = _list



    init{
        viewModelScope.launch{
            dataSource.getNextElectionsFromAPIService()
            val asteroidList = ArrayList<NetworkAsteroid>()
            val listFromDatabaseValueConverted = dataSource
                .getElectionsFromDatabase()

            electionListToDisplayInTheScreen.addSource(_list){
                electionListToDisplayInTheScreen.value = it
            }
        }
    }

    fun reloadFragmentDone() {
        _reloadFragment.value = false
    }
}


//TODO: Create live data val for saved elections

    //TODO: Create val and functions to populate live data for upcoming elections from the API and saved elections from local database

    //TODO: Create functions to navigate to saved or upcoming election voter info
