package com.ghn.shell.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonObject

@Entity(tableName = "samples")
class Sample(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    val name: String,
    val imgUrl: String? = "",
) {
    override fun toString() = "{id : $id, name : $name, imgUrl : $imgUrl}"
}