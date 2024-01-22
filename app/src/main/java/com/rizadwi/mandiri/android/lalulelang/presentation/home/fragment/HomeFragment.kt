package com.rizadwi.mandiri.android.lalulelang.presentation.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.rizadwi.mandiri.android.lalulelang.adapter.AuctionAdapter
import com.rizadwi.mandiri.android.lalulelang.core.base.BaseFragment
import com.rizadwi.mandiri.android.lalulelang.databinding.FragmentHomeBinding
import com.rizadwi.mandiri.android.lalulelang.model.AuctionModel
import com.rizadwi.mandiri.android.lalulelang.presentation.bid.BidActivity
import com.rizadwi.mandiri.android.lalulelang.presentation.home.HomeMainActivity
import com.rizadwi.mandiri.android.lalulelang.util.NavigationUtil
import com.rizadwi.mandiri.android.lalulelang.util.data.UIState
import com.rizadwi.mandiri.android.lalulelang.viewmodel.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var auctionAdapter: AuctionAdapter

    @Inject
    lateinit var navigation: NavigationUtil


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
        auctionAdapter.setOnAuctionClicked(::onAuctionClicked)
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

            binding.pbLoading.visibility = View.GONE
            binding.tvError.visibility = View.GONE
            binding.rvAuction.visibility = View.GONE

            when (it) {
                UIState.Loading -> binding.pbLoading.visibility = View.VISIBLE
                is UIState.Error -> handleAuctionError(it.error)
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
        binding.rvAuction.visibility = View.VISIBLE
        auctionAdapter.setData(data)
    }

    private fun handleAuctionError(err: Error) {
        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = err.message
    }

    private fun onAuctionClicked(auction: AuctionModel) {
        navigation.moveForResult(
            requireActivity() as AppCompatActivity,
            BidActivity::class.java,
            HomeMainActivity.INIT_FRAGMENT_CODE,
            mapOf(BidActivity.AUCTION_ID to auction.id)
        )
    }

}