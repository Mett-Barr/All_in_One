package com.example.allinone.page2.internet.network

import com.squareup.moshi.Json

data class MarsPhoto(
    val id: String,
    // used to map img_src from the JSON to imgSrcUrl in our class
    @Json(name = "img_src") val imgSrcUrl: String
)