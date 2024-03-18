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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.viewModel = viewModel

        initViewModelCallback()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.init()
    }

    private fun initViewModelCallback() {
        with(viewModel){
            companyList.observe(viewLifecycleOwner) { setCompanyList() }
            clubList.observe(viewLifecycleOwner) { setClubList() }
            loading.observe(viewLifecycleOwner) {
                loading.value?.let { it -> setLoadingBar(it) }
            }
        }
    }

    /**
     * 회사 리스트
     */
    private fun setCompanyList(){
        val companys = viewModel.getCompanyList()
        binding.llSortCompany.removeAllViews()

        for (i in 0 until 3){
            val company = companys?.get(i)
            val view = LayoutInflater.from(context).inflate(R.layout.item_company_view, null)
            val img = view.findViewById<ImageView>(R.id.search_brand_hot_brand_iv)
            val name = view.findViewById<TextView>(R.id.search_brand_hot_brand_tv)

            Glide.with(this).load(company?.imgPath).into(img)
            name.text = company?.name

            view.setOnClickListener{
                viewModel.clickCompany(name.text.toString())
            }
            binding.llSortCompany.addView(view)
        }
    }

    /**
     * 동아리 리스트
     */
    private fun setClubList(){
        val clubList = viewModel.getClubList()

        val adapter = clubList?.let { ClubAdapter(context, it) }
        binding.lvClub.adapter = null
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
    }

    private fun setLoadingBar(flag: Boolean) {
        Log.i(tag, "setLoadingBar:")
        if (flag){
            binding.ivHomeLoading.visibility = View.VISIBLE
            binding.rlHomeLoading.visibility = View.VISIBLE
            try {
                Glide.with(context)
                    .load(R.drawable.loading_bar)
                    .into(binding.ivHomeLoading)
            } catch (e: Exception) {
                Log.i(tag, "startLoading Exception: ${e.message}")
            }
        }
        else{
            binding.ivHomeLoading.visibility = View.GONE
            binding.rlHomeLoading.visibility = View.GONE
        }
    }
}