package com.example.newszapp.ui.screen

import android.content.Context
import android.widget.Toast
import com.example.newszapp.R
import com.example.newszapp.data.News
import java.text.SimpleDateFormat

fun showToastNotImplemented(context: Context) {
    Toast.makeText(
        context,
        context.getString(R.string.action_not_implemented),
        Toast.LENGTH_LONG
    ).show()
}

fun findNewsById(list: List<News>, id: Int): News? {
    list.forEach {
        if (it.id == id) {
            return it
        }
    }

    return null
}

fun getDateTimeFromUTC(utcDate: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter = SimpleDateFormat("EEE d MMM yyyy HH:mm:ss z")
    val formattedDate = formatter.format(parser.parse(utcDate))

    return formattedDate.toString()
}