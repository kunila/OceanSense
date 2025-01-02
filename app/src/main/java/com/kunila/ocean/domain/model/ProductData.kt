package com.kunila.ocean.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductData(
   val id: Int,
   val name: String,
   val description: String,
   val price: Double,
   val image: String?,
   val quantity: Int?
): Parcelable
