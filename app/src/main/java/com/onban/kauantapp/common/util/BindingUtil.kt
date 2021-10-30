package com.onban.kauantapp.common.util

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onban.network.data.CompanyEntity

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
    val tokens = date.split("T")[0].split("-")
    text = tokens[1] + "-" + tokens[2]
}

@BindingAdapter("textFromCompanyLogo")
fun TextView.setTextFromCompanyLogo(companyEntity: CompanyEntity) {
    val back = GradientDrawable().apply {
        cornerRadius = 20f
        color = ColorStateList.valueOf(Color.parseColor(companyEntity.backgroundColor))
    }
    this.background = back
    this.text = companyEntity.logo
    this.layoutParams?.let {
        it.height = RandUtil.getRandInt(300, 500)
        this.layoutParams = it
    }
}