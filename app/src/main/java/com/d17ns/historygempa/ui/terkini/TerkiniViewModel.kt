package com.d17ns.historygempa.ui.terkini

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d17ns.historygempa.network.Api
import com.d17ns.historygempa.network.DataGempaTerkini
import com.d17ns.historygempa.network.GempaTerkini
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// class TerkiniViewModel sebagai ViewModel untuk ui Gempa 5.0+ SR
class TerkiniViewModel : ViewModel() {

    // LiveData variabel _dataGempaTerkini dari data class DataGempaTerkini
    private val _dataGempaTerkini = MutableLiveData<List<DataGempaTerkini>>()
    fun dataGempaTerkini(): LiveData<List<DataGempaTerkini>> = _dataGempaTerkini

    // function findGempaTerkini()
    fun findGempaTerkini() {

        // mendefinisikan variabel client untuk mengakses function getGempaTerkini() dengan retrofitService
        val client = Api.retrofitService.getGempaTerkini()

        // retrofit enqueue untuk mengirimkan request
        client.enqueue(object: Callback<GempaTerkini> {

            //function onResponse untuk mendefinisikan jika request direspon
            // retrofit Call dan Response untuk data class GempaTerkini
            override fun onResponse (
                call: Call<GempaTerkini>, response: Response<GempaTerkini>
            ) {

                // jika respon berhasil, maka akan menampilkan data dari LiveData _dataGempaTerkini
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _dataGempaTerkini.postValue(responseBody.infogempa.gempa)
                    }
                }

                // jika request gagal, akan mengirimkan log gagal
                else {
                    Log.e("History", "onFailure: ${response.message()}")
                }
            }

            // function onFailure apabila request tidak direspon, maka akan menampilkan log error
            override fun onFailure(
                call: Call<GempaTerkini>, t: Throwable) {
                Log.e("History", "onFailure: ${t.message}")
            }
        })
    }
}