package com.d17ns.historygempa.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// constant variabel BASE_URL untuk mendefinisikan master URL dari file JSON yang akan digunakan
private const val BASE_URL = "https://data.bmkg.go.id/DataMKG/TEWS/"

// build moshi object yang akan digunakan oleh Retrofit
// KotlinJsonAdapter ditambahkan agar kompatibel dengan kotlin
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

// retrofit builder untuk build retrofit object menggunakan Moshi converter dengan moshi object
// baseUrl(BASE_URL) untuk memanggil URL dari variabel BASE_URL yang sudah didefinisikan
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// interface ApiService untuk memanggil file json dari master URL
// memanggil 2 file json dari master URL yang sama menggunakan retrofit HTTP @GET
// gempadirasakan.json untuk informasi gempa terbaru
// gempaterkini.json untuk informasi gempa terkini dengan filter magnitude diatas 5.0 SR
// function getGempaDirasakan() memanggil data class GempaDirasakan.kt menggunakan retrofit call
// function getGempaTerkini() memanggil data class GempaTerkini.kt menggunakan retrofit call
interface ApiService {
    @GET("gempadirasakan.json")
    fun getGempaDirasakan(): Call<GempaDirasakan>

    @GET("gempaterkini.json")
    fun getGempaTerkini(): Call<GempaTerkini>
}

// object Api untuk mengekspos retrofit service dengan lazy-initialize ApiService
object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}