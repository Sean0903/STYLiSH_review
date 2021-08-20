package com.sean.stylish_review.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product (
    val title: String = "",
    val id: Int = -1
): Parcelable