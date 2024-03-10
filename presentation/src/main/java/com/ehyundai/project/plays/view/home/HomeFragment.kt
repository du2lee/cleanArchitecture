package com.ehyundai.project.plays.view.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ehyundai.project.plays.view.Club
import com.ehyundai.project.plays.view.ClubAdapter
import com.ehyundai.project.plays.R
import com.ehyundai.project.plays.databinding.FragmentHomeBinding
import com.ehyundai.project.plays.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var context: Context

    /** dummy Data -> API 생성 시 삭제 */

    var groupList = arrayListOf<Club>(
        Club(R.drawable.ic_shinchan, "슛솔랭", "2022.10.10", "안녕하세요"),
        Club(R.drawable.ic_shinchan, "스뚝파", "2022.10.16", "안녕하세요"),
        Club(R.drawable.ic_shinchan, "러닝", "2011.09.10", "안녕하세요"),
        Club(R.drawable.ic_shinchan, "자바락", "2022.11.04", "안녕하세요"),
        Club(R.drawable.ic_shinchan, "밴드", "2022.11.04", "안녕하세요"),
        Club(R.drawable.ic_shinchan, "뀨르", "2022.11.04", "안녕하세요"),
    )

    /** dummy Data -> API 생성 시 삭제 */

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        for (i in 0 until 10){
            val view = LayoutInflater.from(context).inflate(R.layout.item_company_view, null)
            binding.llSortCompany.addView(view)
        }

        val adapter = ClubAdapter(context, groupList)
        binding.lvClub.adapter = adapter

        /**
         * 클릭 이벤트
         */
//        binding.lvClub.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
//            val item = groupList.get(position)
//
//            val intent = Intent(context, ClubActivity::class.java)
//            intent.putExtra("name", item.name)
//            intent.putExtra("date", item.date)
//            startActivity(intent)
//        }

        return binding.root
    }
}