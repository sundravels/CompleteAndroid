package com.example.data.model

import com.example.database.model.ImagesEntity
import com.example.network.model.NetworkImage


fun NetworkImage.asEntity() = ImagesEntity(
    id = this.user.id?:"",
    name = this.user.name,
    fullUrl = this.urls.full,
    regularUrl = this.urls.regular,
    smallUrl = this.urls.small
)