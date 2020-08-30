package com.playerthong.androidnews.utils

import androidx.annotation.NonNull
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object{
        @NonNull
        fun getSimpleDate(@NonNull strDate: String?): String? {
            try {
                val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
                val dateFormat2: DateFormat = SimpleDateFormat("EEE, d MMM yyyy", Locale.US)
                val date: Date = dateFormat.parse(strDate)
                return dateFormat2.format(date)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            return ""
        }
    }

}