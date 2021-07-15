package com.example.android.politicalpreparedness.ui.launch

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.politicalpreparedness.data.data_objects.dto.Division
import com.example.android.politicalpreparedness.databinding.FragmentLaunchBinding

class LaunchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentLaunchBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.representativeButton.setOnClickListener { navToRepresentatives() }
        binding.upcomingButton.setOnClickListener { navToElections() }
        binding.voterInfoButton.setOnClickListener{ navToVoterInfo() }

        return binding.root
    }

    private fun navToElections() {
        this.findNavController()
            .navigate(LaunchFragmentDirections
                .actionLaunchFragmentToElectionsFragment())
    }

    /*2, Division("1", "Chile", "Region Metropolitana"*/

    private fun navToRepresentatives() {
        this.findNavController()
            .navigate(LaunchFragmentDirections
                .actionLaunchFragmentToRepresentativeFragment())
    }

    private fun navToVoterInfo() {
        val aux = Division("1", "Chile", "Region Metropolitana")
        this.findNavController()
            .navigate(LaunchFragmentDirections
                .actionLaunchFragmentToVoterInfoFragment(
                    2, aux
                ))
    }

}
