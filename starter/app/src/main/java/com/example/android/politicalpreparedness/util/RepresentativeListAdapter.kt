package com.example.android.politicalpreparedness.util

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.data.data_objects.dto.Representative
import com.example.android.politicalpreparedness.data.data_objects.dto.Channel
import com.example.android.politicalpreparedness.databinding.ViewHolderItemRepresentativeBinding

class RepresentativeListAdapter(
    private val onClickListener: OnClickListener)
    : ListAdapter<Representative, RepresentativeListAdapter.RepresentativeViewHolder>(RepresentativeDiffCallback) {

    class RepresentativeViewHolder(val binding: ViewHolderItemRepresentativeBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Representative) {
            binding.representativeNameTextView.text = item.official.name
            binding.representativePhotoImageView.setImageResource(R.drawable.ic_profile)

            //TODO: Show social links ** Hint: Use provided helper methods
            //TODO: Show www link ** Hint: Use provided helper methods

            binding.executePendingBindings()
        }
        private fun showSocialLinks(channels: List<Channel>) {
            val facebookUrl = getFacebookUrl(channels)
            if (!facebookUrl.isNullOrBlank()) { enableLink(binding.representativeFacebookImageView, facebookUrl) }

            val twitterUrl = getTwitterUrl(channels)
            if (!twitterUrl.isNullOrBlank()) { enableLink(binding.representativeTwitterImageView, twitterUrl) }
        }

        private fun showWWWLinks(urls: List<String>) {
            enableLink(binding.representativeWWWImageView, urls.first())
        }

        private fun getFacebookUrl(channels: List<Channel>): String? {
            return channels.filter { channel -> channel.type == "Facebook" }
                .map { channel -> "https://www.facebook.com/${channel.id}" }
                .firstOrNull()
        }

        private fun getTwitterUrl(channels: List<Channel>): String? {
            return channels.filter { channel -> channel.type == "Twitter" }
                .map { channel -> "https://www.twitter.com/${channel.id}" }
                .firstOrNull()
        }

        private fun enableLink(view: ImageView, url: String) {
            view.visibility = View.VISIBLE
            view.setOnClickListener { setIntent(url) }
        }

        private fun setIntent(url: String) {
            val uri = Uri.parse(url)
            val intent = Intent(ACTION_VIEW, uri)
            itemView.context.startActivity(intent)
        }
    }



    /**
     * Callback for calculating the diff between two non-null items in a list.
     *
     * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
     * list that's been passed to `submitList`.
     */
    //TODO: Add companion object to inflate ViewHolder (from)
    //TODO: Create RepresentativeDiffCallback
    companion object RepresentativeDiffCallback : DiffUtil.ItemCallback<Representative>() {
        override fun areItemsTheSame(
            oldItem: Representative,
            newItem: Representative): Boolean {
            return oldItem.official == newItem.official
        }

        override fun areContentsTheSame(
            oldItem: Representative,
            newItem: Representative): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RepresentativeViewHolder, position: Int) {
        val representative = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(representative)
        }

        holder.bind(representative)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepresentativeViewHolder {
        return RepresentativeViewHolder(ViewHolderItemRepresentativeBinding.inflate(LayoutInflater.from(parent.context)))
    }

    //TODO: Create RepresentativeListener
    class OnClickListener(val clickListener: (representative: Representative) -> Unit) {
        fun onClick(representative: Representative) = clickListener(representative)
    }
}







