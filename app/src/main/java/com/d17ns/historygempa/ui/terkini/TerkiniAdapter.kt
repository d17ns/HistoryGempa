package com.d17ns.historygempa.ui.terkini

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d17ns.historygempa.R
import com.d17ns.historygempa.databinding.ListGempaTerkiniBinding
import com.d17ns.historygempa.network.DataGempaDirasakan
import com.d17ns.historygempa.network.DataGempaTerkini

// class TerkiniAdapter untuk menampilkan list data yang ada di file gempaterkini.json
// menggunakan adapter RecyclerView adapter untuk menampilkan datanya
// mendefinisikan variabel listGempaTerkini dengan List<DataGempaTerkini>
class TerkiniAdapter(private val listGempaTerkini: List<DataGempaTerkini>,
                       private val context: Context
) :
    RecyclerView.Adapter<TerkiniAdapter.TerkiniViewHolder>() {

    // inner class TerkiniViewHolder dengan binding ListGempaTerkiniBinding
    inner class TerkiniViewHolder(itemView: View, val binding: ListGempaTerkiniBinding) :
        RecyclerView.ViewHolder(itemView) {
    }

    // function onCreateViewHolder untuk plot data ke dalam layout list_gempa_terkini
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TerkiniViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_gempa_terkini, parent, false)
        val binding = ListGempaTerkiniBinding.bind(view)
        return  TerkiniViewHolder(view, binding)
    }

    // function onBindViewHolder mendefinisikan variabel dataGempa untuk mengambil data dari variabel listGempaTerkini
    override fun onBindViewHolder(holder: TerkiniViewHolder, position: Int) {
        val dataGempa : DataGempaTerkini = listGempaTerkini[position]

        // holder.binding.apply untuk mendefinisikan elemen data yang ditampilkan sesuai layout list_gempa_terkini
        holder.binding.apply {

            // mendefinisikan elemen-elemen TextView yang ada di dalam layout list_gempa_terkini
            // dengan mengakses elemen data tanggal, jam, magnitude, kedalaman, wilayah, dan potensi dari variabel dataGempa
            tanggal.text = dataGempa.tanggal
            jam.text = dataGempa.jam
            magnitude.text = "${dataGempa.magnitude} SR"
            kedalaman.text = dataGempa.kedalaman
            wilayah.text = dataGempa.wilayah
            potensi.text = dataGempa.potensi

            // mendefinisikan fungsi setOnClickListener untuk coordinatesButton dari layout list_gempa_terkini
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

    // function getItemCount() untuk menghitung total data yang ada dari variabel listGempaTerkini
    override fun getItemCount(): Int {
        return listGempaTerkini.size
    }
}