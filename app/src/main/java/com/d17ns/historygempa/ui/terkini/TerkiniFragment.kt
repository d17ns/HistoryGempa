package com.d17ns.historygempa.ui.terkini

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.d17ns.historygempa.databinding.FragmentGempaTerkiniBinding
import com.d17ns.historygempa.ui.dirasakan.DirasakanAdapter

// class DirasakanFragment sebagai fragment class untuk ui Gempa 5.0+ SR
class TerkiniFragment : Fragment() {

    // FragmentGempaTerkiniBinding untuk view binding
    private lateinit var binding : FragmentGempaTerkiniBinding

    // menggunakan class TerkiniViewModel sebagai viewModel
    private val viewModel: TerkiniViewModel by viewModels()

    // function onCreateView untuk inflate layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGempaTerkiniBinding.inflate(layoutInflater)
        return binding.root
    }

    // function gempaTerkiniSetup() untuk mengakses function findGempaTerkini() dari TerkiniViewModel
    // mengimplementasikan RecyclerView sebagai layout
    private fun gempaTerkiniSetup() {
        viewModel.findGempaTerkini()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
        }
        // mengakses function dataGempaTerkini() yang berisikan LiveData dari List<DataGempaTerkini>
        // menggunakan lifecycle observer untuk mengimplementasikan binding RecyclerView dengan adapter TerkiniAdapter
        viewModel.dataGempaTerkini().observe(requireActivity()) { gempaTerkini ->
            binding.recyclerView.adapter = context?.let {
                TerkiniAdapter(
                    gempaTerkini, it
                )
            }
        }
    }

    // function onViewCreated untuk mengakses function gempaTerkiniSetup()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gempaTerkiniSetup()
    }
}