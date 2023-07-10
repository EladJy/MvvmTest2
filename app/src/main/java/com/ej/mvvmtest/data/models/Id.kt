package com.ej.mvvmtest.data.models

import com.google.gson.annotations.SerializedName

data class Id(
  @SerializedName("kind") var kind: String? = null,
  @SerializedName("videoId") var videoId: String? = null,
)