package com.ehyundai.project.plays.view.club.board

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ehyundai.project.plays.databinding.FragmentClubBoardBinding
import com.ehyundai.project.plays.view.club.ClubViewModel

class ClubBoardFragment : Fragment() {

    private lateinit var binding: FragmentClubBoardBinding
    private lateinit var context: Context
    private val viewModel by activityViewModels<ClubViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubBoardBinding.inflate(layoutInflater)
        binding.vm = viewModel

        return binding.root
    }
}