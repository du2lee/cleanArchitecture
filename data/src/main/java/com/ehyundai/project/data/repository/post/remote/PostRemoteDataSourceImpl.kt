package com.ehyundai.project.data.repository.post.remote

import android.util.Log
import com.ehyundai.project.data.api.ApiInterface
import com.ehyundai.project.data.api.RetrofitClient
import com.ehyundai.project.data.model.board.BoardResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CountDownLatch
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(private val apiInterface: ApiInterface): PostRemoteDataSource {
//    override fun getPost(token: String, clubNo: String): Single<BoardResponse> {
//        var value: Single<BoardResponse> = Single.just(BoardResponse("", "000", listOf()))
//        val call = RetrofitClient.getApiService(token).getPost(clubNo)
//        call.enqueue(object : Callback<BoardResponse> {
//            override fun onResponse(call: Call<BoardResponse>, response: Response<BoardResponse>) {
//                if (response.isSuccessful) {
//                    val data = response.body() // 응답 데이터 가져오기
//                    value = Single.just(BoardResponse(data!!.status, data!!.code, data!!.data))
//                    Log.i("duhui1", data.toString())
//                } else {
//                    Log.i("duhui2", response.code().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<BoardResponse>, t: Throwable) {
//                // 요청 실패 시 처리
//                Log.i("duhui3", "요청 실패요!")
//            }
//        })
//
//        return value
//    }


    override fun getPost(token: String, clubNo: String): Single<BoardResponse> {
        return Single.create { emitter ->
            val call = RetrofitClient.getApiService(token).getPost(clubNo)
            call.enqueue(object : Callback<BoardResponse> {
                override fun onResponse(call: Call<BoardResponse>, response: Response<BoardResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body() // 응답 데이터 가져오기
                        if (data != null) {
                            emitter.onSuccess(BoardResponse(data.status, data.code, data.data))
                            Log.i("duhui1", data.toString())
                        } else {
                            emitter.onError(Throwable("Response body is null"))
                        }
                    } else {
                        Log.i("duhui2", response.code().toString())
                        emitter.onError(Throwable("Error ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<BoardResponse>, t: Throwable) {
                    // 요청 실패 시 처리
                    Log.i("duhui3", "요청 실패요!")
                    emitter.onError(t)
                }
            })
        }
    }
}