package com.ehyundai.project.plays.view.club

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ehyundai.project.plays.view.club.board.ClubBoardFragment
import com.ehyundai.project.plays.view.club.chat.ClubChatFragment
import com.ehyundai.project.plays.view.club.date.ClubDateFragment
import com.ehyundai.project.plays.view.club.home.ClubHomeFragment

class ClubAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ClubHomeFragment()
            1 -> ClubBoardFragment()
            2 -> ClubDateFragment()
            3 -> ClubChatFragment()
            else -> ClubHomeFragment()
        }
    }

    override fun getItemCount(): Int {
        return 4 // 페이지 수
    }
}