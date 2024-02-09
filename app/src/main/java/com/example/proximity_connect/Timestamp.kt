// TimestampHandler.kt
package com.example.proximity_connect.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.example.proximity_connect.TranscriptEntry

class TimestampHandler {

    companion object {
        fun formatTimestamp(timestamp: Long): String {
            val date = Date(timestamp)
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            return format.format(date)
        }
    }
}

@Composable
fun TimestampText(transcriptEntry: TranscriptEntry, modifier: Modifier = Modifier) {
    val timestamp = transcriptEntry.timestamp
    val text = "${TimestampHandler.formatTimestamp(timestamp)}: ${transcriptEntry.sentence}"
    Text(
        text = text,
        modifier = modifier
    )
}