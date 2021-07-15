package com.example.android.politicalpreparedness.ui.voter_info

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import com.example.android.politicalpreparedness.base.BaseFragment
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.databinding.FragmentVoterInfoBinding
import com.example.android.politicalpreparedness.ui.representative.RepresentativeViewModel
import org.koin.android.ext.android.inject

class VoterInfoFragment : BaseFragment() {

    //TODO: Add ViewModel values and create ViewModel
    /*viewModel = ViewModelProvider(this).get(VoterInfoViewModel::class.java)*/
    //I prefer to use KOIN to inject things. Its good practice to make great test' later
    override val _viewModel: RepresentativeViewModel by inject()


    //TODO: Add binding values
    private lateinit var binding: FragmentVoterInfoBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //TODO: Add binding values
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_voter_info, container, false)
        binding.lifecycleOwner = this

        //TODO: Populate voter info -- hide views without provided data.
        /**
        Hint: You will need to ensure proper data is provided from previous fragment.
        */
        val selectedElection = VoterInfoFragmentArgs.fromBundle(requireArguments()).argElectionId
        val selectedDivision = VoterInfoFragmentArgs.fromBundle(requireArguments()).argDivision


        //TODO: Handle loading of URLs

        //TODO: Handle save button UI state
        //TODO: cont'd Handle save button clicks
        return binding.root
    }

    //TODO: Create method to load URL intents

}