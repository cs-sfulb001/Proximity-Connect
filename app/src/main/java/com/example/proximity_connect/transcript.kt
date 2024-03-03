import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import java.util.*

data class TranscriptEntry(val flagTimestamp: Long, val sentence: String)

// Composable function display a transcript entry with meeting flag
@Composable
fun TranscriptEntryWithMeetingFlag(entry: TranscriptEntry) {
    Row {
        Text(text = "${Date(entry.flagTimestamp)}: ${entry.sentence}")
    }
}

// Composable function to display transcript with meeting flags
@Composable
fun TranscriptWithMeetingFlags(transcript: List<TranscriptEntry>) {
    transcript.forEach { entry ->
        TranscriptEntryWithMeetingFlag(entry = entry)
    }
}