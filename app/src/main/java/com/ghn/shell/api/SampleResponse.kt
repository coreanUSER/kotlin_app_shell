package com.ghn.shell.api

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class SampleResponse (
    val body: Body
)

data class Body (
    val id: String,
    @SerializedName("imageName")
    val name: String,
    val imgUrl: String? = ""
)