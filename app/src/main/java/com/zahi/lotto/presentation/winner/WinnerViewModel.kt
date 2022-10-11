package com.zahi.lotto.presentation.winner

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.zahi.lotto.base.BaseViewModel
import com.zahi.lotto.entity.LotteryItem
import com.zahi.lotto.entity.LotteryNumber
import com.zahi.lotto.repositories.WinnerRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.select.Elements


class WinnerViewModel(private val winnerRepository: WinnerRepository) : BaseViewModel() {
    var lotteryItem: MutableLiveData<ArrayList<LotteryItem>> = MutableLiveData<ArrayList<LotteryItem>>()
    var lotteryNumber: MutableLiveData<LotteryNumber> = MutableLiveData<LotteryNumber>()

    fun getLottoWinnerNumber(drwNo: Long, url: String) {
        winnerRepository.subjectLotteryData.subscribe {
            lotteryNumber.value = it
            doTask(url)
        }
        winnerRepository.getLottoWinnerNumber(drwNo)
    }

    @SuppressLint("CheckResult")
    private fun doTask(url : String) {
        var documentTitle = ""
        val itemList: ArrayList<LotteryItem> = arrayListOf() //MovieItem 배열

        Single.fromCallable {
            try {
                val doc = Jsoup.connect(url).get()

                val elements : Elements = doc.select("table tbody tr")
                run elemLoop@{
                    elements.forEach{
                        val element = it.select("td")
                        val rank = element[0].text()
                        val sumPrize = element[1].text()
                        val people = element[2].text()
                        val prize = element[3].text()

                        val item = LotteryItem(rank, sumPrize, people, prize)
                        itemList.add(item)
                    }
                }

                documentTitle = doc.title()
            } catch (e : Exception) {e.printStackTrace()}

            return@fromCallable documentTitle   // subscribe 호출
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    lotteryItem.value = itemList
                },
                { it.printStackTrace() })
    }
}