// TimestampHandler.kt
package com.example.proximity_connect.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TimestampHandler {

    companion object {
        fun formatTimestamp(timestamp: Long): String {
            val date = Date(timestamp)
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            return format.format(date)
        }
        // Method to generate a timestamp for the current time
        fun generateTimestamp(): Long {
            return System.currentTimeMillis()
        }
        fun addEmphasizedTimestamp(timestamp: Long, emoticon: String) {
            emphasizedTimestamps[timestamp] = emoticon
        }

        fun getEmphasizedTimestamps(): Map<Long, String> {
            return emphasizedTimestamps
        }
    }
}

@Composable
fun TimestampText(timestamp: Long, modifier: Modifier = Modifier) {
    val emphasizedTimestamps = TimestampHandler.getEmphasizedTimestamps()
    val text = if (emphasizedTimestamps.containsKey(timestamp)) {
        "${TimestampHandler.formatTimestamp(timestamp)} ${emphasizedTimestamps[timestamp]}"
    } else {
        TimestampHandler.formatTimestamp(timestamp)
    }
    Text(
        text = text,
        modifier = modifier
    )
}
