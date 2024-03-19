package com.ehyundai.project.plays.view.club

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ehyundai.project.plays.databinding.ActivityClubBinding
import com.google.android.material.tabs.TabLayoutMediator

class ClubActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClubBinding
    private lateinit var context: Context
    private var clubNo: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clubNo = intent.getIntExtra("clubNo", 1)

        binding.vpClub.adapter = ClubAdapter(supportFragmentManager, lifecycle)
        drawTabLayout()
        context = this
    }

    private fun drawTabLayout() {
        // connect ViewPager2
        TabLayoutMediator(binding.tlClub, binding.vpClub) { _, _ -> }.attach()

        // set tabIcon
        binding.tlClub.getTabAt(0)!!.setText("홈")
        binding.tlClub.getTabAt(1)!!.setText("게시판")
        binding.tlClub.getTabAt(2)!!.setText("일정")
        binding.tlClub.getTabAt(3)!!.setText("채팅")
    }
}