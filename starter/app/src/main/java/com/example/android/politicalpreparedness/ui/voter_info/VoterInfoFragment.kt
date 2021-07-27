package com.example.android.politicalpreparedness.ui.voter_info

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.database.getDatabase
import com.example.android.politicalpreparedness.databinding.FragmentVoterInfoBinding
import com.example.android.politicalpreparedness.ui.election.ElectionsFragmentDirections
import com.example.android.politicalpreparedness.util.NavigationCommand
import kotlinx.coroutines.launch

//TODO: Handle loading of URLs: i do this in the xml. i use bindingAdapter's
//TODO: Create method to load URL intents: this is in bindingAdapters

class VoterInfoFragment : Fragment() {

    //TODO: Add binding values
    private lateinit var binding: FragmentVoterInfoBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //TODO: Get bundle values
        /**
        Hint: You will need to ensure proper data is provided from previous fragment.
         */
        val selectedElection = VoterInfoFragmentArgs.fromBundle(requireArguments()).argElectionId
        val selectedDivision = VoterInfoFragmentArgs.fromBundle(requireArguments()).argDivision
        Log.i("TAG", "${selectedDivision}+${selectedElection}")

        //TODO: Add binding values
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_voter_info, container, false)
        binding.lifecycleOwner = this

        //TODO: Add ViewModel values and create ViewModel
        //TODO: Populate voter info -- hide views without provided data: in viewmodel i do this
        //HERE I DONT USE KOIN BECAUSE I WANT TO CREATE THE VIEWMODEL WITH THE ID SAVED IN THE BUNDLE.
        val viewModelFactory = VoterInfoViewModelFactory(selectedElection,
            selectedDivision!!,
            requireActivity().application,
            getDatabase(requireContext()))
        val viewModel = ViewModelProvider(this, viewModelFactory).get(VoterInfoViewModel::class.java)
        binding.viewModel = viewModel

        //TODO: Handle save button UI state
        viewModel.isTheElection.observe(viewLifecycleOwner, Observer{
            if(it != null && it.id == selectedElection){
                binding.savingOrDeletingElectionButton.text = resources.getString(R.string.unfollow_election_button)
            }else {
                binding.savingOrDeletingElectionButton.text = resources.getString(R.string.follow_election_button)
            }
        })

        binding.savingOrDeletingElectionButton.setOnClickListener {
            lifecycleScope.launch{
                if(binding.savingOrDeletingElectionButton.text == "Follow Election"){
                    viewModel.savingElectionInDatabase()
                    viewModel.navigateToElectionsFrag()
                }else{
                    viewModel.deletingElectionInDatabase()
                    viewModel.navigateToElectionsFrag()
                }
            }
        }

        return binding.root
    }
}