package com.rizadwi.mandiri.android.lalulelang.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rizadwi.mandiri.android.lalulelang.core.Constant.Companion.MAX_DESCRIPTION
import com.rizadwi.mandiri.android.lalulelang.databinding.ItemAuctionBinding
import com.rizadwi.mandiri.android.lalulelang.model.AuctionModel
import com.rizadwi.mandiri.android.lalulelang.util.Formatter
import javax.inject.Inject

class AuctionAdapter @Inject constructor(private val formatter: Formatter) :
    Adapter<AuctionAdapter.Holder>() {

    private var onAuctionClicked: ((AuctionModel) -> Unit)? = null
    private var data: List<AuctionModel> = listOf()

    fun setData(data: List<AuctionModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun setOnAuctionClicked(action: (AuctionModel) -> Unit) {
        onAuctionClicked = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemAuctionBinding.inflate(
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
        val auction = data[position]

        with(holder.binding) {
            ivAuction.setImageResource(auction.image)
            tvTitle.text = auction.name
            tvTag.text = auction.topic
            tvDescription.text = formatter.limitText(auction.description, MAX_DESCRIPTION)
            tvPrice.text = formatter.formatIDR(auction.price)
            tvDeadline.text = auction.deadline.toString()
        }

        holder.binding.root.setOnClickListener { onAuctionClicked?.invoke(auction) }

    }


    inner class Holder(val binding: ItemAuctionBinding) : ViewHolder(binding.root)

}