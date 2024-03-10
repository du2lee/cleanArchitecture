package com.ehyundai.project.plays.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.ehyundai.project.plays.databinding.ItemListViewBinding
import com.ehyundai.project.plays.view.Club

class ClubAdapter(val context: Context, private val groupList:ArrayList<Club>) : BaseAdapter() {

    private var mBinding: ItemListViewBinding? = null
    private val binding get() = mBinding!!

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        mBinding = ItemListViewBinding.inflate(LayoutInflater.from(context))

        val logo = binding.ivGroupLogo
        val name = binding.tvGroupName
        val date = binding.tvGroupDate
        val info = binding.tvGroupInfo

        val group = groupList[position]

        logo.setImageResource(group.logo)
        name.text = group.name
        date.text = "Since " + group.date
        info.text = group.info

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