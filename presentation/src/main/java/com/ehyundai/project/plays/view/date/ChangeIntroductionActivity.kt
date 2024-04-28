package com.ehyundai.project.plays.view.date

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ehyundai.project.plays.databinding.ActivityChangePasswordBinding

class ChangeIntroductionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangePasswordBinding
    private val viewModel: ChangeViewModel by viewModels()
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        viewModel.setTitle(5)
        binding.vm = viewModel
        context = this
    }
}