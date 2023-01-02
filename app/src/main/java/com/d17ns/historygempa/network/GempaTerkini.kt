package com.d17ns.historygempa.network

import com.squareup.moshi.Json

// data class GempaTerkini yang mendefinisikan Json object Infogempa
data class GempaTerkini(
    @Json(name = "Infogempa")
    val infogempa: InfoGempaTerkini
)

// data class InfoGempaTerkini yang mendefinisikan list dari Json object gempa
data class InfoGempaTerkini(
    @Json(name = "gempa")
    val gempa: List<DataGempaTerkini>
)

// data class DataGempaTerkini yang mendefinisikan elemen-elemen dari Json object gempa
data class DataGempaTerkini(
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
    @Json(name = "Potensi")
    val potensi: String = ""
)