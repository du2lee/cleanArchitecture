package com.ehyundai.project.plays.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.ehyundai.project.domain.model.Club
import com.ehyundai.project.domain.model.PostEntity
import com.ehyundai.project.plays.databinding.ItemBoardListViewBinding
import com.ehyundai.project.plays.databinding.ItemListViewBinding

class BoardAdapter(val context: Context, private val boardList: ArrayList<PostEntity>) : BaseAdapter() {

    private var mBinding: ItemBoardListViewBinding? = null
    private val binding get() = mBinding!!

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        mBinding = ItemBoardListViewBinding.inflate(LayoutInflater.from(context))

        val view = binding.view
        val img = binding.ivBoardImg
        val user = binding.ivUserImg
        val time = binding.tvTime
        val userText = binding.tvCompanyUser
        val title = binding.tvBoardTitle
        val text = binding.tvBoardText
        val like = binding.tvLike
        val comment = binding.tvComment

        val board = boardList[position]

        // Todo 값 설정 필요
        if(!board.postTitle.isNullOrBlank())
            title.text = board.postTitle
        if(!board.postContent.isNullOrBlank())
            text.text = board.postContent
        if(!board.postImg.isNullOrEmpty())
            Glide.with(context).load(board.postImg!![0].url).into(img)

        return mBinding!!.root
    }

    override fun getCount(): Int {
        return boardList.size
    }

    override fun getItem(position: Int): Any {
        return boardList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}