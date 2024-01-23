package com.rizadwi.mandiri.android.lalulelang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.rizadwi.mandiri.android.lalulelang.core.Constant.Companion.MAX_DESCRIPTION
import com.rizadwi.mandiri.android.lalulelang.databinding.ItemAuctionBinding
import com.rizadwi.mandiri.android.lalulelang.model.AuctionModel
import com.rizadwi.mandiri.android.lalulelang.util.Formatter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AuctionAdapter @Inject constructor(
    private val formatter: Formatter,
    @ApplicationContext private val context: Context
) :
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

        val img = when (auction.topic) {
            "Gadget" -> "https://i.pinimg.com/originals/ca/42/81/ca42818475dc3b69641d6bda0c53e158.jpg"
            "Kendaraan" -> "https://i.pinimg.com/originals/6c/f2/b2/6cf2b2be3f26e3c7dc68b9ccca3cd142.jpg"
            else -> "https://png.pngtree.com/png-clipart/20190520/original/pngtree-question-mark-vector-icon-png-image_4017381.jpg"
        }

        with(holder.binding) {
            Glide.with(context)
                .load(img)
                .into(ivAuction)

            tvTitle.text = auction.name
            tvTag.text = auction.topic
            tvDescription.text = formatter.limitText(auction.description, MAX_DESCRIPTION)
            tvPrice.text = formatter.formatIDR(auction.price)
            tvDeadline.text = formatter.formatDate(auction.deadline)
        }

        holder.binding.root.setOnClickListener { onAuctionClicked?.invoke(auction) }

    }


    inner class Holder(val binding: ItemAuctionBinding) : ViewHolder(binding.root)

}