package com.kanykeinu.cinematica.data.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Genre(val id : Int, val name : String) : Parcelable {
}