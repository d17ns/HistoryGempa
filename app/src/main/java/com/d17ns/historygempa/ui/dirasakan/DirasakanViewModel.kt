package com.d17ns.historygempa.ui.dirasakan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d17ns.historygempa.network.Api
import com.d17ns.historygempa.network.DataGempaDirasakan
import com.d17ns.historygempa.network.GempaDirasakan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// class DirasakanViewModel sebagai ViewModel untuk ui Gempa Dirasakan
class DirasakanViewModel : ViewModel() {

    // LiveData variabel _dataGempaDirasakan dari data class DataGempaDirasakan
    private val _dataGempaDirasakan = MutableLiveData<List<DataGempaDirasakan>>()
    fun dataGempaDirasakan(): LiveData<List<DataGempaDirasakan>> = _dataGempaDirasakan

    // function findGempaDirasakan()
    fun findGempaDirasakan() {
        // mendefinisikan variabel client untuk mengakses function getGempaDirasakan() dengan retrofitService
        val client = Api.retrofitService.getGempaDirasakan()

        // retrofit enqueue untuk mengirimkan request
        client.enqueue(object: Callback<GempaDirasakan> {

            //function onResponse untuk mendefinisikan jika request direspon
            // retrofit Call dan Response untuk data class GempaDirasakan
            override fun onResponse (
                call: Call<GempaDirasakan>, response: Response<GempaDirasakan>
            ) {

                // jika respon berhasil, maka akan menampilkan data dari LiveData _dataGempaDirasakan
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _dataGempaDirasakan.postValue(responseBody.infogempa.gempa)
                    }
                }

                // jika request gagal, akan mengirimkan log gagal
                else {
                        Log.e("History", "onFailure: ${response.message()}")
                }
            }

            // function onFailure apabila request tidak direspon, maka akan menampilkan log error
            override fun onFailure(
                call: Call<GempaDirasakan>, t: Throwable) {
                Log.e("History", "onFailure: ${t.message}")
            }
        })
    }
}