package com.ej.mvvmtest.data.models
import com.google.gson.annotations.SerializedName

data class Thumbnails(
  @SerializedName("default") var default: Default?,
  @SerializedName("medium") var medium: Medium?,
  @SerializedName("high") var high: High?,
)