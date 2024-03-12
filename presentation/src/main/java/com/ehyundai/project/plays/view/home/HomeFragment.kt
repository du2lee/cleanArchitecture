package com.ehyundai.project.plays.view.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.ehyundai.project.plays.view.Club
import com.ehyundai.project.plays.view.ClubAdapter
import com.ehyundai.project.plays.R
import com.ehyundai.project.plays.databinding.FragmentHomeBinding
import com.ehyundai.project.plays.view.main.MainActivity
import com.ehyundai.project.plays.view.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var context: Context
    private val viewModel by activityViewModels<MainViewModel>()

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
        binding.viewModel = viewModel

        initViewModelCallback()

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

    private fun initViewModelCallback() {
        with(viewModel){
            initCompany.observe(viewLifecycleOwner, Observer {
                setCompanyList()
            })
        }
    }

    private fun setCompanyList(){
        val companys = viewModel.getCompanyList()
        Log.i("duhui", companys.toString())

        binding.llSortCompany.removeAllViews()

        for (i in 0 until 3){
            val company = companys?.get(i)
            val view = LayoutInflater.from(context).inflate(R.layout.item_company_view, null)
            val img = view.findViewById<ImageView>(R.id.search_brand_hot_brand_iv)
            val name = view.findViewById<TextView>(R.id.search_brand_hot_brand_tv)
            Glide.with(this)
                .load(company?.imgPath) // 불러올 이미지 url
    //                .placeholder(defaultImage) // 이미지 로딩 시작하기 전 표시할 이미지
    //                .error(defaultImage) // 로딩 에러 발생 시 표시할 이미지
    //                .fallback(defaultImage) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
                .into(img)

            name.text = company?.name

            view.setOnClickListener{
                // 클릭 시 해당 동아리 리스트 노출
            }

            binding.llSortCompany.addView(view)
        }
    }
}