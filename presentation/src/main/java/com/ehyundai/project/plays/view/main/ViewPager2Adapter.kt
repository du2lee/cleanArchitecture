package com.ehyundai.project.plays.view.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ehyundai.project.plays.view.board.BoardFragment
import com.ehyundai.project.plays.view.date.DateFragment
import com.ehyundai.project.plays.view.home.HomeFragment


class ViewPager2Adapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> BoardFragment()
            2 -> DateFragment()
            else -> BoardFragment()
        }
    }

    override fun getItemCount(): Int {
        return 3 // 페이지 수
    }
}