package com.ej.mvvmtest.data.models

import com.google.gson.annotations.SerializedName

data class VideosResponse(
    @SerializedName("kind") var kind: String? = null,
    @SerializedName("etag") var etag: String? = null,
    @SerializedName("nextPageToken") var nextPageToken: String? = null,
    @SerializedName("prevPageToken") var prevPageToken: String? = null,
    @SerializedName("regionCode") var regionCode: String? = null,
    @SerializedName("pageInfo") var pageInfo: PageInfo?,
    @SerializedName("items") var items: ArrayList<Item> = arrayListOf(),
)