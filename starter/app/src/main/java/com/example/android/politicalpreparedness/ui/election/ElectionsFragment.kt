package com.example.android.politicalpreparedness.ui.election

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.android.politicalpreparedness.base.BaseFragment
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.databinding.FragmentElectionBinding
import com.example.android.politicalpreparedness.util.ElectionListAdapter
import com.example.android.politicalpreparedness.util.NavigationCommand
import org.koin.android.ext.android.inject

class ElectionsFragment: BaseFragment() {

    //TODO: Add binding values
    private lateinit var binding: FragmentElectionBinding

    //TODO: Add ViewModel values and create ViewModel
    /*viewModel = ViewModelProvider(this).get(ElectionsViewModel::class.java)*/
    //I prefer to use KOIN to inject things. Its good practice to make great test' later
    override val _viewModel: ElectionsViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        //TODO: Add binding values
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_election,
            container,
            false)
        binding.lifecycleOwner = this
        binding.viewModel = _viewModel

        binding.electionRecyclerView.adapter = ElectionListAdapter(
            ElectionListAdapter.OnClickListener{
                _viewModel.navigationCommand.value = NavigationCommand
                    .To(ElectionsFragmentDirections
                        .actionElectionsFragmentToVoterInfoFragment(it.id,it.division))
            }
        )

        _viewModel.reloadFragment.observe(viewLifecycleOwner, Observer{
            if(it){
                _viewModel.reloadFragmentDone()
                val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
                if (Build.VERSION.SDK_INT >= 26) {
                    ft.setReorderingAllowed(false)
                }
                ft.detach(this).attach(this).commit()
            }
        })




        //TODO: Link elections to voter info

        //TODO: Initiate recycler adapters

        //TODO: Populate recycler adapters

        return binding.root
    }

    //TODO: Refresh adapters when fragment loads

}