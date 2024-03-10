package com.ehyundai.project.data.repository.search

import com.ehyundai.project.domain.model.Club
import com.ehyundai.project.domain.repository.TestRepository
import io.reactivex.Flowable
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(): TestRepository {

    var groupList = arrayListOf<Club>(
        Club("1", "슛솔랭", "2022.10.10", "안녕하세요","1", "2022.10.10"),
        Club("2", "스뚝파", "2022.10.16", "안녕하세요","2", "2022.10.10"),
        Club("3", "러닝", "2011.09.10", "안녕하세요", "3", "2022.10.10"),
        Club("4", "자바락", "2022.11.04", "안녕하세요", "7", "2022.10.10"),
        Club("5", "밴드", "2022.11.04", "안녕하세요", "8", "2022.10.10"),
        Club("6", "뀨르", "2022.11.04", "안녕하세요", "6", "2022.10.10"),
    )

    override fun getClubs(): Flowable<ArrayList<Club>> {
        return Flowable.just(groupList)
    }
}