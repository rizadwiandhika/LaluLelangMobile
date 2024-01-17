package com.rizadwi.mandiri.android.lalulelang.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rizadwi.mandiri.android.lalulelang.databinding.ItemAuctionBinding
import com.rizadwi.mandiri.android.lalulelang.model.AuctionModel
import java.text.NumberFormat
import java.util.Locale

class AuctionAdapter : Adapter<AuctionAdapter.Holder>() {

    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    private var data: List<AuctionModel> = listOf()

    fun setData(data: List<AuctionModel>) {
        this.data = data
        notifyDataSetChanged()
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

        val formattedPrice = currencyFormat.format(auction.price)
        val formattedTime = auction.deadline.toString()

        with(holder.binding) {
            ivAuction.setImageResource(auction.image)
            tvTitle.text = auction.name
            tvTag.text = auction.topic
            tvDescription.text = this@AuctionAdapter.liimtDescription(auction.description)
            tvPrice.text = formattedPrice
            tvDeadline.text = formattedTime
        }
    }

    private fun liimtDescription(description: String): String {
        if (description.length < 100) {
            return description
        }
        return description.slice(IntRange(0, 100)) + "..."
    }

    inner class Holder(val binding: ItemAuctionBinding) : ViewHolder(binding.root)

}