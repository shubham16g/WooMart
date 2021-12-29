package com.shubhamgupta16.woomart.network.wpmodel

import com.google.gson.annotations.SerializedName

data class JWTData(
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("nicename")
    val nicename: String,
    @SerializedName("token")
    val token: String
)