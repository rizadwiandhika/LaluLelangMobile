package com.rizadwi.mandiri.android.lalulelang.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rizadwi.mandiri.android.lalulelang.R
import com.rizadwi.mandiri.android.lalulelang.core.Constant.Companion.MAX_DESCRIPTION
import com.rizadwi.mandiri.android.lalulelang.databinding.ItemBidBinding
import com.rizadwi.mandiri.android.lalulelang.model.BidModel
import com.rizadwi.mandiri.android.lalulelang.util.Formatter
import javax.inject.Inject

class PurchaseAdapter @Inject constructor(private val formatter: Formatter) :
    Adapter<PurchaseAdapter.Holder>() {
    inner class Holder(val binding: ItemBidBinding) : ViewHolder(binding.root)

    private var data: List<BidModel> = listOf()
    private var onPurchaseClickListener: ((BidModel) -> Unit)? = null


    fun setBids(data: List<BidModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun setOnPurchaseClickListener(action: (BidModel) -> Unit) {
        onPurchaseClickListener = action
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemBidBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val bid = data[position]

        with(holder.binding) {
            ivAuction.setImageResource(bid.image)
            tvTitle.text = bid.name
            tvTag.text = bid.status
            tvDeadline.text = bid.deadline.toString()
            tvDescription.text = formatter.limitText(bid.description, MAX_DESCRIPTION)
            tvBidAmount.text = formatter.formatIDR(bid.amount)

            tvTag.setBackgroundResource(R.color.green)

            root.setOnClickListener { onPurchaseClickListener?.invoke(bid) }
        }
    }


}