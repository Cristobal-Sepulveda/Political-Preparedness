package com.example.android.politicalpreparedness.util


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.databinding.ViewHolderItemElectionBinding

class ElectionListAdapter(private val onClickListener: OnClickListener)
    : ListAdapter<ELECTION_DOMAIN_OBJECT, ElectionListAdapter.ElectionViewHolder>(ElectionDiffCallback) {

    //TODO: Create ElectionViewHolder
    class ElectionViewHolder(val binding: ViewHolderItemElectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ELECTION_DOMAIN_OBJECT) {
            binding.electionNameTextView.text = item.name
            binding.electionDateTextView.text = item.electionDay.toString()

            //TODO: Show social links ** Hint: Use provided helper methods
            //TODO: Show www link ** Hint: Use provided helper methods

            binding.executePendingBindings()
        }
    }

    //TODO: Add companion object to inflate ViewHolder (from)
    //TODO: Create ElectionDiffCallback
    companion object ElectionDiffCallback : DiffUtil.ItemCallback<ELECTION_DOMAIN_OBJECT>() {
        override fun areItemsTheSame(
            oldItem: ELECTION_DOMAIN_OBJECT,
            newItem: ELECTION_DOMAIN_OBJECT
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: ELECTION_DOMAIN_OBJECT,
            newItem: ELECTION_DOMAIN_OBJECT
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
        val election = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(election)
        }

        holder.bind(election)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
        return ElectionViewHolder(ViewHolderItemElectionBinding.inflate(LayoutInflater.from(parent.context)))
    }

    //TODO: Create ElectionListener
    class OnClickListener(val clickListener: (election: ELECTION_DOMAIN_OBJECT) -> Unit) {
        fun onClick(election: ELECTION_DOMAIN_OBJECT) = clickListener(election)
    }
}



