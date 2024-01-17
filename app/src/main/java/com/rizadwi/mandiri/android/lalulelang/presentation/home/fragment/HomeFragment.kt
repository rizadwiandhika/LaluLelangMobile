package com.rizadwi.mandiri.android.lalulelang.presentation.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.rizadwi.mandiri.android.lalulelang.adapter.AuctionAdapter
import com.rizadwi.mandiri.android.lalulelang.base.BaseFragment
import com.rizadwi.mandiri.android.lalulelang.databinding.FragmentHomeBinding
import com.rizadwi.mandiri.android.lalulelang.model.AuctionModel
import com.rizadwi.mandiri.android.lalulelang.util.data.UIState
import com.rizadwi.mandiri.android.lalulelang.viewmodel.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    private val auctionAdapter = AuctionAdapter()

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        setupUI()
        observeViewModel()
        fetchData()
    }


    private fun setupUI() {
        binding.rvAuction.adapter = auctionAdapter
        binding.svFilter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.prepareFilter(newText ?: "")
                return true
            }
        })
    }

    private fun observeViewModel() {
        viewModel.auctionLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Error -> handleAuctionError(it.error)
                UIState.Loading -> manageAuctionVisibility(AuctionState.LOADING)
                is UIState.Success -> handleAuctionSuccess(it.data)
            }
        }

    }


    private fun fetchData() {
        viewModel.fetchListAuction()
    }


    private fun handleAuctionSuccess(data: List<AuctionModel>) {
        if (data.isEmpty()) {
            handleAuctionError(Error("There is no currently active auctions"))
            return
        }
        manageAuctionVisibility(AuctionState.SUCCESS)
        auctionAdapter.setData(data)
    }

    private fun handleAuctionError(err: Error) {
        manageAuctionVisibility(AuctionState.ERROR)
        binding.tvError.text = err.message
    }

    private enum class AuctionState {
        LOADING, ERROR, SUCCESS
    }


    private fun manageAuctionVisibility(status: AuctionState) {
        when (status) {
            AuctionState.LOADING -> {
                binding.pbLoading.visibility = View.VISIBLE
                binding.tvError.visibility = View.GONE
                binding.rvAuction.visibility = View.GONE
            }

            AuctionState.ERROR -> {
                binding.pbLoading.visibility = View.GONE
                binding.tvError.visibility = View.VISIBLE
                binding.rvAuction.visibility = View.GONE
            }

            AuctionState.SUCCESS -> {
                binding.pbLoading.visibility = View.GONE
                binding.tvError.visibility = View.GONE
                binding.rvAuction.visibility = View.VISIBLE
            }
        }


    }


}