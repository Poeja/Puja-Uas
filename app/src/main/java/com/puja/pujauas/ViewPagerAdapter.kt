package com.puja.pujauas

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3  // Number of tabs or fragments
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment()  // Replace with your MovieFragment
            1 -> TVFragment()     // Replace with your TVFragment
            2 -> AccountFragment() // Replace with your ProfileFragment or AccountFragment
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}
