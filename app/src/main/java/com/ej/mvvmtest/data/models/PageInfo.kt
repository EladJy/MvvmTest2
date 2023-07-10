package com.ej.mvvmtest.data.models

import com.google.gson.annotations.SerializedName

data class PageInfo(
  @SerializedName("totalResults") var totalResults: Int? = null,
  @SerializedName("resultsPerPage") var resultsPerPage: Int? = null,
)