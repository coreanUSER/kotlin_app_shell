package com.ghn.shell.api

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class SampleResult (
    val body: Body
)

data class Body (
    val name: String,
    val age: Int,
    @SerializedName("detail")
    val detailInfo: JSONObject? = null
)