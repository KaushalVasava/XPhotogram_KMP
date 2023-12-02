package com.kaushalvasava.apps.instagram.util

import kotlinx.datetime.Instant

object DateUtil {

    fun getDateTime(
        milliseconds: Long,
    ): String {
        val instant = Instant.fromEpochMilliseconds(milliseconds)
        return instant.toString().substringBefore("T")
    }
}