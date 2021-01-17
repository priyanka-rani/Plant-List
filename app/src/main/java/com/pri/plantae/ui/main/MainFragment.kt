package com.pri.plantae.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import com.pri.plantae.adapters.PlantAdapter
import com.pri.plantae.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val adapter = PlantAdapter()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.plantList.adapter = adapter
        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
        binding.refreshLayout.setOnRefreshListener {
            adapter.refresh()
        }
        adapter.addLoadStateListener {
            if (it.refresh != LoadState.Loading)
                binding.refreshLayout.isRefreshing = false
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            viewModel.getPlantList().collectLatest {
                adapter.submitData(it)
            }
        }
    }

}