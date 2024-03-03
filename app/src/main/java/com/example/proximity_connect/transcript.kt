import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import java.util.Date

data class TranscriptEntry(val timestamp: Long, val sentence: String, val hasMeetingFlag: Boolean = false)

// Composable function to display a transcript entry
@Composable
fun TranscriptEntryWithTimestampAndFlag(entry: TranscriptEntry) {
    Row {
        Text(text = "${Date(entry.timestamp)}: ${entry.sentence} ${if (entry.hasMeetingFlag) "[FLAG]" else ""}")
    }
}

// Composable function to display the transcript
@Composable
fun TranscriptWithTimestampsAndFlags(transcript: List<TranscriptEntry>) {
    transcript.forEach { entry ->
        TranscriptEntryWithTimestampAndFlag(entry = entry)
    }
}