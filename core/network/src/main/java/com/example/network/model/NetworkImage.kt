package com.example.network.model

import kotlinx.serialization.Serializable


@Serializable
data class NetworkImage(
    val user: User = User(),
    val urls: Urls = Urls()
)

@Serializable
data class User(
    val id: String? = null,
    val name: String? = null
)

@Serializable
data class Urls(
    val full: String? = null,
    val regular: String? = null,
    val small: String? = null
)