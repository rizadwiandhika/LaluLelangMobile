package com.rizadwi.mandiri.android.lalulelang.presentation.home.fragment.bidtab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
import androidx.fragment.app.viewModels
import com.rizadwi.mandiri.android.lalulelang.R
import com.rizadwi.mandiri.android.lalulelang.adapter.PurchaseAdapter
import com.rizadwi.mandiri.android.lalulelang.core.Constant.Companion.UNKNOWN_ERROR
import com.rizadwi.mandiri.android.lalulelang.core.base.BaseFragment
import com.rizadwi.mandiri.android.lalulelang.dataaccess.dto.courier.CourierResponse
import com.rizadwi.mandiri.android.lalulelang.databinding.DialogCreateDeliveryBinding
import com.rizadwi.mandiri.android.lalulelang.databinding.FragmentBidMyWinningBidBinding
import com.rizadwi.mandiri.android.lalulelang.model.BidModel
import com.rizadwi.mandiri.android.lalulelang.util.ConfirmDialogUtil
import com.rizadwi.mandiri.android.lalulelang.util.ToastUtil
import com.rizadwi.mandiri.android.lalulelang.util.data.UIState
import com.rizadwi.mandiri.android.lalulelang.viewmodel.bid.BidViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyWinningBidFragment : BaseFragment<FragmentBidMyWinningBidBinding>() {

    private val viewModel: BidViewModel by viewModels()

    @Inject
    lateinit var purchaseAdapter: PurchaseAdapter

    @Inject
    lateinit var confirmDialog: ConfirmDialogUtil

    @Inject
    lateinit var toast: ToastUtil

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBidMyWinningBidBinding {
        return FragmentBidMyWinningBidBinding.inflate(inflater, container, false)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        setupUI()
        observeViewModel()
        fetchData()
    }


    private fun setupUI() {
        binding.rvPurchase.adapter = purchaseAdapter
    }

    private fun observeViewModel() {
        viewModel.purchaseListLiveData.observe(viewLifecycleOwner) {
            binding.tvError.visibility = View.GONE
            binding.rvPurchase.visibility = View.GONE
            binding.pbLoading.visibility = View.GONE

            when (it) {
                UIState.Loading -> binding.pbLoading.visibility = View.VISIBLE
                is UIState.Error -> handleError(it.error, true)
                is UIState.Success -> handleSuccess(it.data)
            }
        }

        viewModel.courierListLiveData.observe(viewLifecycleOwner) {
            when (it) {
                UIState.Loading -> {}
                is UIState.Error -> toast.show(it.error.message ?: UNKNOWN_ERROR)
                is UIState.Success -> purchaseAdapter.setOnPurchaseClickListener(
                    onPurchaseClicked(
                        it.data
                    )
                )
            }
        }
    }

    private fun fetchData() {
        viewModel.fetchAllMyBid()
        viewModel.fetchCourierLIst()
    }

    private fun onPurchaseClicked(courierList: List<CourierResponse>): (BidModel) -> Unit {

        return {
            val dialogBinding =
                DialogCreateDeliveryBinding.inflate(LayoutInflater.from(requireActivity()))

            val spinnerAdapter =
                ArrayAdapter(
                    requireContext(),
                    support_simple_spinner_dropdown_item,
                    courierList.map { it.courierName })

            with(dialogBinding) {
                rgDelivery.check(R.id.rbNo)
                spCourier.adapter = spinnerAdapter
                rgDelivery.setOnCheckedChangeListener { _, checkedId ->
                    when (checkedId) {
                        R.id.rbNo -> llForDelivery.visibility = View.GONE
                        R.id.rbYes -> llForDelivery.visibility = View.VISIBLE
                    }
                }
            }

            confirmDialog.show(dialogBinding) { close ->
                close.invoke()
            }
        }
    }

    private fun handleSuccess(data: List<BidModel>) {
        if (data.isEmpty()) {
            return handleError(Error("You don't have any winning bid yet!"))
        }
        binding.rvPurchase.visibility = View.VISIBLE
        purchaseAdapter.setBids(data)
    }

    private fun handleError(e: Error, withToast: Boolean = false) {
        val message = e.message ?: "Unknown error occurred!"
        if (withToast) {
            confirmDialog.show("Failed", message, "Alright", null)
        }

        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = message
    }

}