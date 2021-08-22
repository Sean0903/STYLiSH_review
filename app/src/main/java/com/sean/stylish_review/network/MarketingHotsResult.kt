package com.sean.stylish_review.network

import android.os.Parcelable
import com.sean.stylish_review.home.DataItem
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class MarketingHotsResult(
    val error: String? = null,
    @Json(name = "data") val hotsList:@RawValue List<Hots>? = null
) : Parcelable {

    fun toHomeItems(): List<DataItem> {
        val items = mutableListOf<DataItem>()

        hotsList?.let {
            for ((title, products) in it) {
                items.add(DataItem.Header(title))
                for ((index, product) in products.withIndex()) {
                    when (index % 2) {
                        0 -> items.add(DataItem.Single(product))
                        1 -> items.add(DataItem.Multiple(product))
                    }
                }
            }
        }
        return items
    }
}

//拿取資料的格式
@Parcelize
data class CatalogListResult(
    val data: List<Product>,
    @Json(name = "next_paging")
    val nextPaging: Int? = null
) : Parcelable


@Parcelize
data class Product(
    val id : Long,
    val title : String,
    val description : String,
    val price : Int,
    val texture : String,
    val wash : String,
    val place : String,
    val note : String,
    val story : String,
    val colors : List<Color>,
    val sizes : List<String>,
    val variants : List<Variant>,
    @Json(name = "main_image")
    val mainImage : String,
    val images : List<String>
): Parcelable

@Parcelize
data class Color(
    val code: String,
    val name: String
) : Parcelable

@Parcelize
data class Variant(
    @Json(name = "color_code")
    val color_code : String,
    val size : String,
    val stock : Int
) : Parcelable

@Parcelize
data class Hots(
    val title : String,
    val products : List<Product>  //product 的話會接不到資料，因為跟Api的格式不一樣
) : Parcelable