package com.example.todolist.db

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToDoEntity")
data class ToDoEntity (
    @PrimaryKey(autoGenerate = true) var id : Int? = null,
    @ColumnInfo(name="title") var title : String,
    @ColumnInfo(name="importance") var importance : Int
) : Parcelable {
    // Parcelable 구현 부분
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id ?: -1)
        parcel.writeString(title)
        parcel.writeInt(importance)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ToDoEntity> {
        override fun createFromParcel(parcel: Parcel): ToDoEntity {
            return ToDoEntity(parcel)
        }

        override fun newArray(size: Int): Array<ToDoEntity?> {
            return arrayOfNulls(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt()
    )
}
