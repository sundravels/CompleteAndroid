package com.sundravels.androidbestpractices.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


public interface ImageService{

    @GET("raw/wgkJgazE")
    suspend fun callApi():Response<ResponseBody>

}