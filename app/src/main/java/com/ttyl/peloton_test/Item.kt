package com.ttyl.peloton_test

import com.squareup.moshi.Json

data class Item(@field:Json(name="userId")val userId: String,
                @field:Json(name="id")val id: String,
                @field:Json(name="title")val title: String,
                @field:Json(name="body")val body: String) {
}