package com.ehyundai.project.plays.view.board

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ehyundai.project.plays.databinding.FragmentBoardBinding
import com.ehyundai.project.plays.view.main.MainViewModel

class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding
    private lateinit var context: Context
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoardBinding.inflate(layoutInflater)
        binding.vm = viewModel

        return binding.root
    }

}