package com.ej.mvvmtest.data.models

import com.google.gson.annotations.SerializedName

data class Item(
  @SerializedName("kind") var kind: String? = null,
  @SerializedName("etag") var etag: String? = null,
  @SerializedName("id") var id: Id?,
  @SerializedName("snippet") var snippet: Snippet?,
)