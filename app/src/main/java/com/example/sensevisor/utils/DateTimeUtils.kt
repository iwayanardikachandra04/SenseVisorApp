package com.example.sensevisor.utils

import android.os.Handler
import android.os.Looper
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    fun startDateTimeUpdater(tvTime: TextView, tvDate: TextView) {
        val handler = Handler(Looper.getMainLooper())

        val timeFormat = SimpleDateFormat("hh:mm a", Locale("in", "ID"))
        timeFormat.timeZone = TimeZone.getTimeZone("Asia/Jakarta")

        val dateFormat = SimpleDateFormat("dd MMMM yyyy - EEEE", Locale("in", "ID"))
        dateFormat.timeZone = TimeZone.getTimeZone("Asia/Jakarta")

        val runnable = object : Runnable {
            override fun run() {
                val now = Calendar.getInstance().time
                tvTime.text = timeFormat.format(now)
                tvDate.text = dateFormat.format(now)
                handler.postDelayed(this, 1000)
            }
        }

        handler.post(runnable)
    }
}
