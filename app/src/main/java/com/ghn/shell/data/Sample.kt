package com.ghn.shell.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "samples")
class Sample{
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = UUID.randomUUID().toString()

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "imgUrl")
    var imgUrl: String = ""

    @ColumnInfo(name = "created")
    var created: Date = Date()

    override fun toString() = "{id : $id, name : $name, imgUrl : $imgUrl, created : $created}"
}