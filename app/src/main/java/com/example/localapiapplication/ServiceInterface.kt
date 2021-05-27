package com.example.localapiapplication

import retrofit2.Call
import retrofit2.http.*

interface ServiceInterface {
    @GET("Kontak")
    fun getData(): Call<List<KontakData>>

    @GET("Kontak")
    fun getDatawithId(@Query("id") id:Int): Call<List<KontakData>>

    @POST("Kontak")
    fun postKontak(@Body kontakData: KontakData): Call<KontakData>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Kontak", hasBody = true)
    fun deleteKontak(@Field("id") id: Int): Call<KontakData>

    @FormUrlEncoded
    @HTTP(method="PUT", path="Kontak", hasBody = true)
    fun updateKontak(
        @Field("id") id: Int,
        @Field("nama") nama: String,
        @Field("nomor") nomor: String): Call<KontakData>
}