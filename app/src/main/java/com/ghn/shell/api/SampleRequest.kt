package com.ghn.shell.api

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class SampleRequest (
    val id: String,
    val name: String,
    val imgUrl: String? = ""
)