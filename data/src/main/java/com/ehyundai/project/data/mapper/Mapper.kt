package com.ehyundai.project.data.mapper

import com.ehyundai.project.data.model.auth.LoginEntity
import com.ehyundai.project.data.model.board.BoardEntity
import com.ehyundai.project.data.model.board.BoardImg
import com.ehyundai.project.data.model.board.BoardResponse
import com.ehyundai.project.data.model.board.CommentEntity
import com.ehyundai.project.data.model.club.ClubEntity
import com.ehyundai.project.data.model.club.ClubInfoEntity
import com.ehyundai.project.data.model.club.MemberEntity
import com.ehyundai.project.data.model.company.CompanyEntity
import com.ehyundai.project.data.model.members.AuthResponse
import com.ehyundai.project.data.model.members.BaseResponse
import com.ehyundai.project.domain.model.AuthCode
import com.ehyundai.project.domain.model.Club
import com.ehyundai.project.domain.model.ClubInfo
import com.ehyundai.project.domain.model.CommentEntityForDomain
import com.ehyundai.project.domain.model.Company
import com.ehyundai.project.domain.model.Login
import com.ehyundai.project.domain.model.Member
import com.ehyundai.project.domain.model.Post
import com.ehyundai.project.domain.model.PostEntity
import com.ehyundai.project.domain.model.PostImg

/**
 * Data Entity to Data Model
 * Data Layer 에서는 Data Entity 로 받아서 사용하지만, Domain, Presentation Layer 에서는 Data Model 로 사용한다.
 * 즉, Mapper 는 Data Layer 에 존재하면서 다른 계층으로 Data 를 전달할 때, 사용하는 Data Model 에 맞춰서 변환하여 던지는 역할.
 *
 */
fun mapperToClub(clubs: List<ClubEntity>): List<Club> {
    return clubs.toList().map {
        Club(
            it.clubNo,
            it.company,
            it.name,
            it.logo,
            it.members,
            it.date
        )
    }
}

fun mapperToClubInfo(club: ClubInfoEntity): ClubInfo {
    return ClubInfo(
        club.clubNo,
        club.company?: "미지정",
        club.name,
        club.clubDesc,
        club.logo,
        club.date,
        mapperToMember(club.members))
}

fun mapperToMember(members: List<MemberEntity>): List<Member>{
    return members.toList().map {
        Member(
            it.memberNo,
            it.memberNm?: "미지정",
            it.nickname?: "미지정",
            it.companyNm?: "미지정",
            it.role?: "미지정",
            it.profileImg?: "미지정",
            it.introduction?: "미지정",
        )
    }

}

fun mapperToCompany(companys: List<CompanyEntity>): List<Company> {
    return companys.toList().map {
        Company(
            it.companyNo,
            it.name,
            it.imgPath
        )
    }
}

fun mapperToAuthCode(response: AuthResponse): AuthCode {
    return AuthCode(
        response.status?: "실패",
        response.code?: "실패",
        response.data?: "-1"
    )
}

fun mapperToReponse(response: BaseResponse): AuthCode {
    return AuthCode(
        response.status?: "실패",
        response.code?: "실패",
        response.message?: "-1"
    )
}

fun mapperToLogin(response: LoginEntity): Login {
    return Login(
        response.email?: "",
        response.auth?: "",
        response.accessToken?: "",
        response.refreshToken?: ""
    )
}

fun mapperToPost(response: BoardResponse): Post {
    return Post(
        response.status?: "",
        response.code?: "",
        mapperToPostEntity(response.data)
    )
}

fun mapperToPostEntity(response: List<BoardEntity>?): List<PostEntity>{
    if(response.isNullOrEmpty()){
        return listOf()
    }
    return response.toList().map {
        PostEntity(
            it.postNo?: "",
            it.nickname?: "",
            it.companyNm?: "",
            it.postTitle?: "",
            it.postContent?: "",
            it.likeCnt?: "",
            listOf(),
            it.createdDate?: "",
            mapperToPostImg(it.postImg)?: listOf(),
            it.commentCount?: "",
        )
    }
}

fun mapperToCommentEntity(response: List<CommentEntity>): List<CommentEntityForDomain>{
    return response.toList().map {
        CommentEntityForDomain(
            it.postNo?: "",
        )
    }
}

fun mapperToPostImg(response: List<BoardImg>?): List<PostImg>{
    if(response.isNullOrEmpty()){
        return listOf()
    }

    return response.toList().map{
        PostImg(
        it.postImgNo?: "",
        it.postNo?: "",
        it.url?: "")
    }
}

