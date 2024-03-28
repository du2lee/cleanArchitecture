package com.ehyundai.project.plays.view.club.board

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import com.ehyundai.project.plays.Board2Adapter
import com.ehyundai.project.plays.databinding.FragmentClubBoardBinding
import com.ehyundai.project.plays.view.BoardAdapter
import com.ehyundai.project.plays.view.club.ClubActivity
import com.ehyundai.project.plays.view.club.ClubViewModel
import com.ehyundai.project.plays.view.main.MainActivity

class ClubBoardFragment : Fragment() {

    private lateinit var binding: FragmentClubBoardBinding
    private lateinit var context: Context
    private val viewModel by activityViewModels<ClubViewModel>()

    val boardList = arrayListOf("안녕하세요", "24.11.23 모임", "해피해피해피")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as ClubActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClubBoardBinding.inflate(layoutInflater)
        binding.vm = viewModel

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setBoardList()
    }

    private fun setBoardList(){
//        val boardList = viewModel.getBoardList()

        binding.lvBoard.adapter = Board2Adapter(context, boardList)

        // 클릭 이벤트
//        binding.lvBoard.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
//            val intent = Intent(context, ClubActivity::class.java)
//
//            intent.putExtra("clubNo", view.tag.toString().toInt())
//            startActivity(intent)
//        }
    }
}