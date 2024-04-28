package com.ehyundai.project.plays.view.date

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.ehyundai.project.plays.databinding.FragmentDateBinding
import com.ehyundai.project.plays.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DateFragment : Fragment() {

    private lateinit var binding: FragmentDateBinding
    private lateinit var context: Context

    // 데이터를 생성
    val arr = ArrayList<String>(6)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDateBinding.inflate(layoutInflater)
        binding.lvSetting.adapter = setAdapter()

        binding.lvSetting.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->

            when(position){
                0 -> { startActivity(Intent(context, ChangePasswordActivity::class.java)) }
                1 -> { startActivity(Intent(context, ChangeNicknameActivity::class.java)) }
                2 -> { startActivity(Intent(context, ChangeCompanyActivity::class.java)) }
                3 -> { startActivity(Intent(context, ChangePartActivity::class.java)) }
                4 -> { startActivity(Intent(context, ChangeLocationActivity::class.java)) }
                5 -> { startActivity(Intent(context, ChangeIntroductionActivity::class.java)) }
                else -> { startActivity(Intent(context, ChangePasswordActivity::class.java)) }
            }
        }

        return binding.root
    }

    private fun setAdapter(): ArrayAdapter<Any?> {
        arr.add(0, "비밀번호 변경")
        arr.add(1, "닉네임 변경")
        arr.add(2, "소속회사 변경")
        arr.add(3, "부서 변경")
        arr.add(4, "활동지역 변경")
        arr.add(5, "자기소개")

        val listAdapter: ArrayAdapter<Any?> =
            ArrayAdapter<Any?>(context, R.layout.simple_list_item_1, arr as List<Any?>)

        return listAdapter
    }
}