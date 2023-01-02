package com.d17ns.historygempa.network

import com.squareup.moshi.Json

// data class GempaDirasakan yang mendefinisikan Json object Infogempa
data class GempaDirasakan(
    @Json(name = "Infogempa")
    val infogempa: InfoGempaDirasakan
)

// data class InfoGempaDirasakan yang mendefinisikan list dari Json object gempa
data class InfoGempaDirasakan(
    @Json(name = "gempa")
    val gempa: List<DataGempaDirasakan>
)

// data class DataGempaDirasakan yang mendefinisikan elemen-elemen dari Json object gempa
data class DataGempaDirasakan(
    @Json(name = "Tanggal")
    val tanggal: String = "",
    @Json(name = "Jam")
    val jam: String = "",
    @Json(name = "Coordinates")
    val coordinates: String = "",
    @Json(name = "Magnitude")
    val magnitude: String = "",
    @Json(name = "Kedalaman")
    val kedalaman: String = "",
    @Json(name = "Wilayah")
    val wilayah: String = "",
)