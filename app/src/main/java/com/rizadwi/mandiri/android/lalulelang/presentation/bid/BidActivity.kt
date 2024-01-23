package com.rizadwi.mandiri.android.lalulelang.presentation.bid

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rizadwi.mandiri.android.lalulelang.databinding.ActivityBidBinding
import com.rizadwi.mandiri.android.lalulelang.model.AuctionModel
import com.rizadwi.mandiri.android.lalulelang.presentation.home.HomeMainActivity
import com.rizadwi.mandiri.android.lalulelang.util.Formatter
import com.rizadwi.mandiri.android.lalulelang.util.ToastUtil
import com.rizadwi.mandiri.android.lalulelang.util.data.UIState
import com.rizadwi.mandiri.android.lalulelang.viewmodel.bid.BidViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BidActivity : AppCompatActivity() {

    private val viewModel: BidViewModel by viewModels()

    private lateinit var binding: ActivityBidBinding

    @Inject
    lateinit var toast: ToastUtil

    @Inject
    lateinit var formatter: Formatter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBidBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
        fetchData()
    }


    private fun observeViewModel() {
        viewModel.auctionLiveData.observe(this) {
            when (it) {
                is UIState.Error -> handleAuctionError(it.error)
                UIState.Loading -> manageAuctionState(State.LOADING)
                is UIState.Success -> handleAuctionSuccess(it.data)
            }
        }

        viewModel.createBidLiveData.observe(this) {
            when (it) {
                is UIState.Error -> handleBidError(it.error)
                UIState.Loading -> manageBidState(State.LOADING)
                is UIState.Success -> handleBidSuccess()
            }
        }
    }

    private fun handleBidSuccess() {
        manageBidState(State.SUCCESS)
        intent.putExtra(HomeMainActivity.FRAGMENT_DEST_KEY, HomeMainActivity.BID_FRAGMENT)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun handleBidError(e: Error) {
        manageBidState(State.ERROR)
        toast.show(e.message ?: "Unknown error occurred")
    }

    private fun manageBidState(state: State) {
        binding.btnCreateBid.isEnabled = true
        binding.btnCreateBid.text = "Create bid"

        if (state == State.LOADING) {
            binding.btnCreateBid.isEnabled = false
            binding.btnCreateBid.text = "Placing bid..."
        }
    }

    private fun handleAuctionSuccess(data: AuctionModel) {
        manageAuctionState(State.SUCCESS)
        val img = when (data.topic) {
            "Gadget" -> "https://i.pinimg.com/originals/ca/42/81/ca42818475dc3b69641d6bda0c53e158.jpg"
            "Kendaraan" -> "https://i.pinimg.com/originals/6c/f2/b2/6cf2b2be3f26e3c7dc68b9ccca3cd142.jpg"
            else -> "https://png.pngtree.com/png-clipart/20190520/original/pngtree-question-mark-vector-icon-png-image_4017381.jpg"
        }

        with(binding) {
            Glide.with(this@BidActivity)
                .load(img)
                .into(ivAuction)

            tvTitle.text = data.name
            tvDescription.text = data.description
            tvPrice.text = formatter.formatIDR(data.price)
            tvDeadline.text = formatter.formatDate(data.deadline)

            btnCreateBid.setOnClickListener { viewModel.createBid(etBid.text.toString(), data.id) }
        }
    }

    private fun handleAuctionError(e: Error) {
        manageAuctionState(State.ERROR)
        toast.show(e.message ?: "Unknown error occurred")
    }

    private fun fetchData() {
        val auctionId = intent.getStringExtra(AUCTION_ID)
        if (auctionId == null) {
            toast.show("Auction id is not specified!")
            return
        }
        viewModel.fetchAuctionById(auctionId)
    }


    private fun manageAuctionState(state: State) {
        binding.pbLoading.visibility = View.GONE
        binding.tvError.visibility = View.GONE
        binding.clContainer.visibility = View.GONE

        when (state) {
            State.LOADING -> binding.pbLoading.visibility = View.VISIBLE
            State.SUCCESS -> binding.clContainer.visibility = View.VISIBLE
            State.ERROR -> binding.tvError.visibility = View.VISIBLE
        }
    }

    private enum class State {
        LOADING, SUCCESS, ERROR
    }

    companion object {
        const val AUCTION_ID = "BidActivity.AuctionId"
    }
}