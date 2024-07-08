package com.puja.pujauas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.puja.pujauas.databinding.FragmentTVBinding
import com.puja.pujauas.modul.Television
import com.puja.pujauas.modul.TelevisionResponse
import com.puja.pujauas.service.TVApiInterface
import com.puja.pujauas.service.TVApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVFragment : Fragment() {
    private val tv = arrayListOf<Television>()
    private var _binding: FragmentTVBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTVBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTvList.layoutManager = LinearLayoutManager(context)
        binding.rvTvList.setHasFixedSize(true)
        getTVData { tv: List<Television> ->
            binding.rvTvList.adapter = TVAdapter(tv)
        }
        showRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getTVData(callback: (List<Television>) -> Unit) {
        val apiService = TVApiService.getInstance().create(TVApiInterface::class.java)
        apiService.getTVList().enqueue(object : Callback<TelevisionResponse> {
            override fun onFailure(call: Call<TelevisionResponse>, t: Throwable) {
                // Handle failure
            }

            override fun onResponse(call: Call<TelevisionResponse>, response: Response<TelevisionResponse>) {
                callback(response.body()?.tv ?: emptyList())
            }
        })
    }

    private fun showRecyclerView() {
        binding.rvTvList.layoutManager = LinearLayoutManager(context)
        binding.rvTvList.adapter = TVAdapter(tv)
    }
}
