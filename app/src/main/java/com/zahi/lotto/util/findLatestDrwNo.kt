package com.zahi.lotto.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object findLatestDrwNo {
    @SuppressLint("SimpleDateFormat")
    fun latestDrwNo(): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val startDate = dateFormat.parse("2002-12-07 20:00:00")
        val diff = Date().time - startDate.time
        return (diff / (86400 * 1000 * 7))+1
    }
}