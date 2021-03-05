package com.ghn.shell.api

import retrofit2.http.*
import retrofit2.http.Body

// SampleService? 고민...중
interface SampleNetwork {

    @Headers("")
    @GET("")
    suspend fun getUrl(@Query("id") id: String): String

    @GET("")
    suspend fun getUrl(@Header("") authorize: String,@Query("id") id: String): String

    @GET("")
    suspend fun getAll(@Query("id") id: String): SampleResponse

    @POST("")
    suspend fun setSample(@Body request: SampleRequest)

    @POST("{path}")
    suspend fun setName(@Path("path") path: String, @Query("id") id: String)

//    @POST - 생성
//    @GET - 조회
//    @PUT - 전체 수정
//    @PATCH - 일부 수정
//    @DELETE - 삭제
}