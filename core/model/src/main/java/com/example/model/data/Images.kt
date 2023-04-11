package com.example.model.data

import com.google.gson.annotations.SerializedName


//user image data class
data class Images(
    @SerializedName("id") val id: String? = null,
    @SerializedName("user") val user: User? = null
)

data class User(
    @SerializedName("username") val username: String? = null,
    @SerializedName("profile_image") val profileImage: ProfileImage? = null
)

data class ProfileImage(@SerializedName("medium") val medium: String? = null)