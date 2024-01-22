package com.rizadwi.mandiri.android.lalulelang.presentation.home.fragment.bidtab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rizadwi.mandiri.android.lalulelang.adapter.BidAdapter
import com.rizadwi.mandiri.android.lalulelang.core.base.BaseFragment
import com.rizadwi.mandiri.android.lalulelang.databinding.FragmentBidMyOngoingBidBinding
import com.rizadwi.mandiri.android.lalulelang.util.ToastUtil
import com.rizadwi.mandiri.android.lalulelang.util.data.UIState
import com.rizadwi.mandiri.android.lalulelang.viewmodel.bid.BidViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyOngoingBidFragment : BaseFragment<FragmentBidMyOngoingBidBinding>() {

    private val viewModel: BidViewModel by viewModels()

    @Inject
    lateinit var bidAdapter: BidAdapter

    @Inject
    lateinit var toast: ToastUtil


    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBidMyOngoingBidBinding {
        return FragmentBidMyOngoingBidBinding.inflate(inflater, container, false)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        binding.rvBid.adapter = bidAdapter

        observeViewModel()
        fetchData()
    }


    private fun observeViewModel() {
        viewModel.bidListLiveData.observe(viewLifecycleOwner) {
            binding.rvBid.visibility = View.GONE
            binding.pbLoading.visibility = View.GONE

            when (it) {
                is UIState.Error -> toast.show(it.error.message ?: "Unknown error occurred")
                UIState.Loading -> binding.pbLoading.visibility = View.VISIBLE
                is UIState.Success -> {
                    binding.rvBid.visibility = View.VISIBLE
                    bidAdapter.setBids(it.data)
                }
            }
        }
    }

    private fun fetchData() {
        viewModel.fetchAllMyBid()
    }


}