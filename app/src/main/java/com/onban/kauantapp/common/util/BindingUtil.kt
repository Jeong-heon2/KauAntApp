package com.onban.kauantapp.common.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("textFromString")
fun TextView.setTextFromString(str: String) {
    text = str
}

@BindingAdapter("submitList")
fun <T, VH : RecyclerView.ViewHolder> RecyclerView.submitList(list: List<T>?) {
    list?.let {
        (adapter as ListAdapter<T, VH>).submitList(list)
    }
}

@BindingAdapter("textFromDate")
fun TextView.setTextFromDate(date: String) {
    val test = date.split("T")[0]
    text = date.split("T")[0]
}
