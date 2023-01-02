package com.d17ns.historygempa.ui.dirasakan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.d17ns.historygempa.databinding.FragmentGempaDirasakanBinding

// class DirasakanFragment sebagai fragment class untuk ui Gempa Dirasakan
class DirasakanFragment : Fragment() {

    // FragmentGempaDirasakanBinding untuk view binding
    private lateinit var binding : FragmentGempaDirasakanBinding

    // menggunakan class DirasakanViewModel sebagai viewModel
    private val viewModel: DirasakanViewModel by viewModels()

    // function onCreateView untuk inflate layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGempaDirasakanBinding.inflate(layoutInflater)
        return binding.root
    }

    // function gempaDirasakanSetup() untuk mengakses function findGempaDirasakan() dari DirasakanViewModel
    // mengimplementasikan RecyclerView sebagai layout
    private fun gempaDirasakanSetup() {
        viewModel.findGempaDirasakan()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
        }
        // mengakses function dataGempaDirasakan() yang berisikan LiveData dari List<DataGempaDirasakan>
        // menggunakan lifecycle observer untuk mengimplementasikan binding RecyclerView dengan adapter DirasakanAdapter
        viewModel.dataGempaDirasakan().observe(requireActivity()) {
                gempaDirasakan ->  binding.recyclerView.adapter = context?.let {
                DirasakanAdapter(
                    gempaDirasakan, it
                )
            }
        }
    }

    // function onViewCreated untuk mengakses function gempaDirasakanSetup()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gempaDirasakanSetup()
    }
}