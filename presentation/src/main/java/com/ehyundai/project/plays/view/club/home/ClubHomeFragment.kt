package com.ehyundai.project.plays.view.club.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.ehyundai.project.plays.databinding.FragmentClubHomeBinding
import com.ehyundai.project.plays.view.club.ClubActivity
import com.ehyundai.project.plays.view.club.ClubViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClubHomeFragment : Fragment() {

    private lateinit var binding: FragmentClubHomeBinding
    private lateinit var context: Context
    private val viewModel by activityViewModels<ClubViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as ClubActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubHomeBinding.inflate(layoutInflater)
        binding.vm = viewModel

        initViewModelCallback()

        return binding.root
    }

    private fun initViewModelCallback() {
        with(viewModel){
            clubInfo.observe(viewLifecycleOwner) {
                Glide.with(context).load(it.logo).into(binding.ivThumbnail)
                binding.tvClubIntro.text = it.clubDesc
            }
        }
    }
}