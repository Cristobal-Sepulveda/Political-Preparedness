package com.example.android.politicalpreparedness.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.*
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.politicalpreparedness.R
import com.example.android.politicalpreparedness.data.data_objects.domain_object.ELECTION_DOMAIN_OBJECT
import com.example.android.politicalpreparedness.data.data_objects.dto.Representative


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

@BindingAdapter("profileImage")
fun fetchImage(view: ImageView, src: String?) {
    //TODO: Add Glide call to load image and circle crop - user ic_profile as a placeholder and for errors.
    val uri = (src ?: "").toUri().buildUpon().scheme("https").build()
    Glide.with(view)
        .load(uri)
        .placeholder(R.drawable.ic_profile)
        .error(R.drawable.ic_broken_image)
        .circleCrop()
        .into(view)
}

@BindingAdapter("stateValue")
fun Spinner.setNewValue(value: String?) {
    val adapter = toTypedAdapter<String>(this.adapter as ArrayAdapter<*>)
    val position = when (adapter.getItem(0)) {
        is String -> adapter.getPosition(value)
        else -> this.selectedItemPosition
    }
    if (position >= 0) {
        setSelection(position)
    }
}

@BindingAdapter("isVisible")
fun isButtonVisible(button : Button, data : Boolean){
    if(data){
        button.isEnabled = true
        button.setBackgroundResource(R.color.colorPrimaryDark)
    }else{
        button.isEnabled = false
        button.setBackgroundResource(R.color.white)
    }
}

inline fun <reified T> toTypedAdapter(adapter: ArrayAdapter<*>): ArrayAdapter<T> {
    return adapter as ArrayAdapter<T>
}
