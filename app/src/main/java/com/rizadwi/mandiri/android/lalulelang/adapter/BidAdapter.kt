package com.rizadwi.mandiri.android.lalulelang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.rizadwi.mandiri.android.lalulelang.R
import com.rizadwi.mandiri.android.lalulelang.core.Constant.Companion.MAX_DESCRIPTION
import com.rizadwi.mandiri.android.lalulelang.databinding.ItemBidBinding
import com.rizadwi.mandiri.android.lalulelang.model.BidModel
import com.rizadwi.mandiri.android.lalulelang.util.Formatter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BidAdapter @Inject constructor(
    private val formatter: Formatter,
    @ApplicationContext private val context: Context
) :
    Adapter<BidAdapter.Holder>() {
    inner class Holder(val binding: ItemBidBinding) : ViewHolder(binding.root)

    private var data: List<BidModel> = listOf()


    fun setBids(data: List<BidModel>) {
        this.data = data
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
            Glide.with(context)
                .load(bid.image)
                .into(ivAuction)

            tvTitle.text = bid.name
            tvTag.text = translateStatus(bid.status)
            tvDeadline.text = bid.deadline.toString()
            tvDescription.text = formatter.limitText(bid.description, MAX_DESCRIPTION)
            tvBidAmount.text = formatter.formatIDR(bid.amount)

            tvTag.setBackgroundResource(getStatusColor(bid.status))
        }
    }

    private fun translateStatus(status: String): String {
        return when (status) {
            "VALID" -> "Highest Bid"
            "INVALID" -> "Lose"
            else -> "Unknown"
        }
    }

    private fun getStatusColor(status: String): Int {
        return when (status) {
            "VALID" -> R.color.blue
            "INVALID" -> R.color.orange
            else -> R.color.white
        }
    }


}