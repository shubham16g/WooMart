package com.shubhamgupta16.woomart.network.wpmodel
import com.google.gson.annotations.SerializedName

data class JWTModel(
    @SerializedName("code")
    val code: String,
    @SerializedName("data")
    val data: JWTData,
    @SerializedName("message")
    val message: String,
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("success")
    val success: Boolean
)


