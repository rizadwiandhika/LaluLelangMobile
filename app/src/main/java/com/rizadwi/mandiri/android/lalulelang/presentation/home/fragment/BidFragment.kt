package com.rizadwi.mandiri.android.lalulelang.presentation.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.rizadwi.mandiri.android.lalulelang.core.base.BaseFragment
import com.rizadwi.mandiri.android.lalulelang.databinding.FragmentBidBinding
import com.rizadwi.mandiri.android.lalulelang.presentation.home.fragment.bidtab.MyOngoingBidFragment
import com.rizadwi.mandiri.android.lalulelang.presentation.home.fragment.bidtab.MyWinningBidFragment

class BidFragment : BaseFragment<FragmentBidBinding>() {

    private lateinit var viewPagerAdapter: BidTabAdapter

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBidBinding {
        return FragmentBidBinding.inflate(inflater, container, false)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        val fragments = listOf(MyOngoingBidFragment(), MyWinningBidFragment())
        viewPagerAdapter = BidTabAdapter(this, fragments)

        setupUI()
    }

    private fun setupUI() {
        val viewPager = binding.vpMessage
        val tabLayout = binding.tabMessage

        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "On-Going Bid"
                1 -> tab.text = "Winning Bid"
            }
        }.attach()
        viewPager.setCurrentItem(0, false)
    }

    private inner class BidTabAdapter(
        parent: Fragment,
        private val contents: List<Fragment>
    ) : FragmentStateAdapter(parent.childFragmentManager, parent.lifecycle) {


        override fun getItemCount(): Int {
            return contents.size
        }

        override fun createFragment(position: Int): Fragment {
            return contents[position]
        }

    }
}