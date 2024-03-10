package com.ehyundai.project.plays.view.main

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ehyundai.project.plays.R
import com.ehyundai.project.plays.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.vm = viewModel
        binding.vpMain.adapter = ViewPager2Adapter(supportFragmentManager, lifecycle)
        drawTabLayout()
        context = this
    }

    private fun drawTabLayout(){
        // connect ViewPager2
        TabLayoutMediator(binding.tlMain, binding.vpMain) { _, _ -> }.attach()

        // set tabIcon
        binding.tlMain.getTabAt(0)!!.setIcon(R.drawable.ic_search)
        binding.tlMain.getTabAt(1)!!.setIcon(R.drawable.ic_group)
        binding.tlMain.getTabAt(2)!!.setIcon(R.drawable.ic_profile)
    }
}