package com.example.android.politicalpreparedness.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.data_objects.domain_object.Representative


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ELECTION_DOMAIN_OBJECT>?) {

    val adapter = recyclerView.adapter as ElectionListAdapter
    adapter.submitList(data)

}

@BindingAdapter("representativeData")
fun bindRecyclerView2(recyclerView: RecyclerView, data: List<Representative>?) {

    val adapter = recyclerView.adapter as RepresentativeListAdapter
    adapter.submitList(data)

}

@BindingAdapter("linkOnClick")
fun bindOnTextClickListener(textView: TextView, url: String?) {

    textView.setOnClickListener { view: View? ->
        if (url != null && url != "") {
            val context: Context = textView.context
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)

            context.startActivity(intent)
        }
    }
}

/*
@BindingAdapter("app:loadImage")
fun loadImage(view: ImageView, url: String?) {
    val uri = (url ?: "").toUri().buildUpon().scheme("https").build()

    Glide.with(view).load(uri)
        .placeholder(R.drawable.ic_profile)
        .error(R.drawable.ic_profile)
        .circleCrop()
        .into(view)
}*/
