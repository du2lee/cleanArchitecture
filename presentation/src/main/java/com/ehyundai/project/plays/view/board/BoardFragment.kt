package com.ehyundai.project.plays.view.board

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ehyundai.project.plays.databinding.FragmentBoardBinding
import com.ehyundai.project.plays.view.BoardAdapter
import com.ehyundai.project.plays.view.ClubAdapter
import com.ehyundai.project.plays.view.main.MainActivity

class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding
    private lateinit var context: Context
    private val viewModel by activityViewModels<BoardViewModel>()

    private var boardList = arrayListOf<String>("안녕하세요", "24.11.23 모임", "해피해피해피")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoardBinding.inflate(layoutInflater)
        binding.vm = viewModel

        viewModel.getBoard(context, "1")
        initViewModelCallback()

        return binding.root
    }

    private fun setBoardList(){
        val boardList = viewModel.getBoardList()

        binding.lvBoard.adapter = boardList?.let { BoardAdapter(context, it) }

        // 클릭 이벤트
//        binding.lvBoard.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
//            val intent = Intent(context, ClubActivity::class.java)
//
//            intent.putExtra("clubNo", view.tag.toString().toInt())
//            startActivity(intent)
//        }
    }

    private fun initViewModelCallback() {
        with(viewModel){
            boardList.observe(viewLifecycleOwner) { setBoardList() }
        }
    }
}