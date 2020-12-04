package com.example.androidsampleapp.service

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import retrofit2.http.GET

/**
 * The [retrofit2.Retrofit] interface that creates our API service.
 */
interface BackendApi {

    @GET("/test/home")
    suspend fun getPage(): Response
}

@Parcelize
data class Response (
    @SerializedName("page") var page: Page? = null
): Parcelable

@Parcelize
data class Page (
    @SerializedName("cards") var cards: List<CardObject>? = null
): Parcelable

@Parcelize
data class CardObject (
    @SerializedName("card_type") var cardType: String = "",
    @SerializedName("card") var card: Card? = null
): Parcelable

@Parcelize
data class Card (
    @SerializedName("image") var image: Image? = null,
    @SerializedName("title") var title: Info? = null,
    @SerializedName("description") var description: Info? = null,
    @SerializedName("value") var value: String = "",
    @SerializedName("attributes") var attributes: Attributes? = null
): Parcelable

@Parcelize
data class Image (
    @SerializedName("url") var url: String = "",
    @SerializedName("size") var size: Size? = null
): Parcelable

@Parcelize
data class Size (
    @SerializedName("height") var height: Int = 0,
    @SerializedName("width") var width: Int = 0
): Parcelable

@Parcelize
data class Info (
    @SerializedName("value") var value: String = "",
    @SerializedName("attributes") var attributes: Attributes? = null
): Parcelable

@Parcelize
data class Attributes (
    @SerializedName("text_color") var textColor: String = "FF615D5D",
    @SerializedName("font") var font: Font? = null,
): Parcelable

@Parcelize
data class Font (
    @SerializedName("size") var size: Int = 15,
): Parcelable