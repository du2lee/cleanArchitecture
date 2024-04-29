package com.ehyundai.project.plays.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.ehyundai.project.domain.model.Club
import com.ehyundai.project.plays.databinding.ItemListViewBinding

class ClubAdapter(val context: Context, private val groupList: ArrayList<Club>) : BaseAdapter() {

    private var mBinding: ItemListViewBinding? = null
    private val binding get() = mBinding!!

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        mBinding = ItemListViewBinding.inflate(LayoutInflater.from(context))

        val view = binding.view
        val logo = binding.ivGroupLogo
        val name = binding.tvGroupName
        val date = binding.tvGroupDate
        val info = binding.tvGroupInfo

        val group = groupList[position]

        view.tag = group.clubNo
        Glide.with(context).load(group.logo).into(logo)
        name.text = group.name
        date.text = "Since " + group.date.substring(0, 10)
        info.text = group.company + " | 멤버" + group.members

        return mBinding!!.root
    }

    override fun getCount(): Int {
        return groupList.size
    }

    override fun getItem(position: Int): Any {
        return groupList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}