package com.onban.network.data

import com.google.gson.annotations.SerializedName

data class CompanyEntity (
    @SerializedName("name") val name: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("backgroundColor") val backgroundColor: String,
    @SerializedName("textColor") val textColor: String,
)
data class CompanyRes (
    @SerializedName("code") val code: Int,
    @SerializedName("result") val result: List<CompanyEntity>
)