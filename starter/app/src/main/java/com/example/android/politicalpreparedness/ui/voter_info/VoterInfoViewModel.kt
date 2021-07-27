package com.example.android.politicalpreparedness.ui.voter_info

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.android.politicalpreparedness.base.BaseViewModel
import com.example.android.politicalpreparedness.data.data_objects.dbo.ELECTION_DBO
import com.example.android.politicalpreparedness.data.data_objects.dbo.asDomainModel
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ADMINISTRATIONBODY_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.data_objects.domain_object.DIVISION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.data_objects.domain_object.asDatabaseModel
import com.example.android.politicalpreparedness.data.data_objects.dto.ADMINISTRATIONBODY_DTO
import com.example.android.politicalpreparedness.data.data_objects.dto.VoterInfoResponse
import com.example.android.politicalpreparedness.data.data_objects.dto.asDomainModel
import com.example.android.politicalpreparedness.data.database.ElectionDatabase
import com.example.android.politicalpreparedness.data.network.CivicApi
import com.example.android.politicalpreparedness.ui.election.ElectionsFragmentDirections
import com.example.android.politicalpreparedness.util.NavigationCommand
import kotlinx.coroutines.launch

//VIEWMODEL THAT IS CREATED AS THE ORIGINAL IDEA WAS.
class VoterInfoViewModel(val electionId: Int,
                         val division: DIVISION_DOMAIN_OBJECT,
                         val app: Application,
                         val dataBase: ElectionDatabase) : BaseViewModel(app) {

    //TODO: Add live data to hold voter info
    private val _electionClicked = MutableLiveData<ELECTION_DOMAIN_OBJECT>()
    val electionClicked: LiveData<ELECTION_DOMAIN_OBJECT>
        get() = _electionClicked

    private val _administrationBodyDO = MutableLiveData<ADMINISTRATIONBODY_DOMAIN_OBJECT>()
    val administrationBodyDO: LiveData<ADMINISTRATIONBODY_DOMAIN_OBJECT>
        get() = _administrationBodyDO

    val isTheElection: LiveData<ELECTION_DBO> = dataBase.electionDao.getElection(electionId)

    var domainELECTIONInScreen:
            MediatorLiveData<ELECTION_DOMAIN_OBJECT> = MediatorLiveData()

    //TODO: Add var and methods to support loading URLs
    var domainELECTIONLinksInScreen:
            MediatorLiveData<ADMINISTRATIONBODY_DOMAIN_OBJECT> = MediatorLiveData()

    init {
        viewModelScope.launch {
            try {
                val requestResponse = CivicApi.RETROFIT_SERVICE_CIVIC_API.getVoterInfo(
                    "AIzaSyBNGDAG_qIAaCtZGcDftVXgI75-CwcWg64",
                    electionId,
                    "${division.state}, ${division.country}")
                populateVoterInfoXML(requestResponse)
            }catch(e: Exception){
                showSnackBar.value = e.message
            }
        }
    }

    //TODO: Add var and methods to populate voterInfo XML
    fun populateVoterInfoXML(voterInfoResponse: VoterInfoResponse) {
        val electionToScreen = voterInfoResponse.election.asDomainModel(
            voterInfoResponse.election)
        _electionClicked.value = electionToScreen

        domainELECTIONInScreen.addSource(_electionClicked) {
            domainELECTIONInScreen.value = it
        }

        val gettingAdministrationBody = voterInfoResponse.state[0].electionAdministrationBody
        val gettingAdministrationBodyDO = gettingAdministrationBody.asDomainModel(
            gettingAdministrationBody)

        _administrationBodyDO.value = gettingAdministrationBodyDO
        domainELECTIONLinksInScreen.addSource(_administrationBodyDO) {
            domainELECTIONLinksInScreen.value = it
        }
    }

    //TODO: Add var and methods to save and remove elections to local database
    suspend fun savingElectionInDatabase(){
        dataBase.electionDao.insertElection(domainELECTIONInScreen.value!!.asDatabaseModel(
            domainELECTIONInScreen.value!!
        ))
    }

    suspend fun deletingElectionInDatabase(){
        dataBase.electionDao.unfollowElection(electionId)
    }

    //TODO: Create functions to navigate to the ElectionListFragment.
    fun navigateToElectionsFrag(){
        navigationCommand.value = NavigationCommand.To(VoterInfoFragmentDirections
            .actionVoterInfoFragmentToElectionsFragment())
    }

    //TODO: cont'd -- Populate initial state of save button to reflect
    // proper action based on election saved status
    /**
     * Hint: The saved state can be accomplished in multiple ways.
     * It is directly related to how elections are saved/removed from the database.
     */
}



