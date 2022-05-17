package com.zahi.lotto.presentation.winner

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zahi.lotto.entity.LotteryNumber
import com.zahi.lotto.repositories.WinnerRepository
import com.zahi.themovieapp.base.BaseViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WinnerViewModel(private val winnerRepository: WinnerRepository) : BaseViewModel() {
    var drwNo: MutableLiveData<Int> = MutableLiveData<Int>()

    fun getLottoWinnerNumber() {
        viewModelScope.launch {
            winnerRepository.getLottoWinnerNumber(drwNo.value!!)
                .enqueue(object : Callback<LotteryNumber> {
                    override fun onResponse(
                        call: Call<LotteryNumber>,
                        response: Response<LotteryNumber>
                    ) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                Log.d("Repository", "Movies: $responseBody")
                            } else {
                                Log.d("Repository", "Failed to get response")
                            }
                        }
                    }

                    override fun onFailure(call: Call<LotteryNumber>, t: Throwable) {
                        Log.e("Repository", "onFailure", t)
                    }
                })
        }
    }

}