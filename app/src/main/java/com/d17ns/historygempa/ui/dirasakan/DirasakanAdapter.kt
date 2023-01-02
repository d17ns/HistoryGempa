package com.d17ns.historygempa.ui.dirasakan

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d17ns.historygempa.R
import com.d17ns.historygempa.databinding.ListGempaDirasakanBinding
import com.d17ns.historygempa.network.DataGempaDirasakan

// class DirasakanAdapter untuk menampilkan list data yang ada di file gempadirasakan.json
// menggunakan adapter RecyclerView adapter untuk menampilkan datanya
// mendefinisikan variabel listGempaDirasakan dengan List<DataGempaDirasakan>
class DirasakanAdapter(private val listGempaDirasakan: List<DataGempaDirasakan>,
private val context: Context
) :
RecyclerView.Adapter<DirasakanAdapter.DirasakanViewHolder>() {

    // inner class DirasakanViewHolder dengan binding ListGempaDirasakanBinding
    inner class DirasakanViewHolder(itemView: View, val binding: ListGempaDirasakanBinding) :
        RecyclerView.ViewHolder(itemView) {
    }

    // function onCreateViewHolder untuk plot data ke dalam layout list_gempa_dirasakan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirasakanViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.list_gempa_dirasakan, parent, false)
        val binding = ListGempaDirasakanBinding.bind(view)
        return  DirasakanViewHolder(view, binding)
    }

    // function onBindViewHolder mendefinisikan variabel dataGempa untuk mengambil data dari variabel listGempaDirasakan
    override fun onBindViewHolder(holder: DirasakanViewHolder, position: Int) {
        val dataGempa : DataGempaDirasakan = listGempaDirasakan[position]

        // holder.binding.apply untuk mendefinisikan elemen data yang ditampilkan sesuai layout list_gempa_dirasakan
        holder.binding.apply {
            // mendefinisikan elemen-elemen TextView yang ada di dalam layout list_gempa_dirasakan
            // dengan mengakses elemen data tanggal, jam, magnitude, kedalaman, dan wilayah dari variabel dataGempa
            tanggal.text = dataGempa.tanggal
            jam.text = dataGempa.jam
            magnitude.text = "${dataGempa.magnitude} SR"
            kedalaman.text = dataGempa.kedalaman
            wilayah.text = dataGempa.wilayah

            // mendefinisikan fungsi setOnClickListener untuk coordinatesButton dari layout list_gempa_dirasakan
            // menggunakan intent untuk membuat fungsi agar button dapat mengakses google maps
            // akses google maps berdasarkan elemen data coordinate dari variabel dataGempa
            coordinatesButton.setOnClickListener {
                val url = "https://www.google.com/maps/place/${dataGempa.coordinates}"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                context.startActivity(intent)
            }
        }
    }

    // function getItemCount() untuk menghitung total data yang ada dari variabel listGempaDirasakan
    override fun getItemCount(): Int {
        return listGempaDirasakan.size
    }
}