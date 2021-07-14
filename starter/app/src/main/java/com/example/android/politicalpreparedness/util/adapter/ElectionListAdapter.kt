package com.example.android.politicalpreparedness.util.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.android.politicalpreparedness.data.network.models.Election

class ElectionListAdapter(private val clickListener: ElectionListener): ListAdapter<Election, ElectionViewHolder>(ElectionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
        return ElectionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    //TODO: Bind ViewHolder

    //TODO: Add companion object to inflate ViewHolder (from)
}

//TODO: Create ElectionViewHolder

//TODO: Create ElectionDiffCallback

//TODO: Create ElectionListener