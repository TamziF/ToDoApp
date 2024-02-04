package com.example.todo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID

enum class Importance { LOW, MEDIUM, HIGH }

@Entity(tableName = "ToDoItems")
data class ToDoItem(
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var importance: Importance,
    @ColumnInfo(name = "deadline")
    var deadLine: String = "",
    @ColumnInfo(name = "creation_date")
    val creationDate: String = SimpleDateFormat("HH:mm dd.MMM", Locale("RUS")).format(Timestamp(System.currentTimeMillis())),
    @ColumnInfo
    var isDone: Boolean = false,
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
)