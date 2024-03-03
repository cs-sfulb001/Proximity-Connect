import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.clickable
import com.example.proximity_connect.TranscriptEntry
import java.text.SimpleDateFormat
import java.util.*

class MeetingFlagHandler {

    companion object {
        // Function to format meeting flag timestamp
        fun formatMeetingFlagTimestamp(timestamp: Long): String {
            val date = Date(timestamp)
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            return format.format(date)
        }

        // Function to add meeting flag to transcript entry
        fun addMeetingFlag(entry: TranscriptEntry, flagTimestamp: Long): TranscriptEntry {
            // In this function, you might want to add the flag timestamp to the TranscriptEntry
            // You can modify the TranscriptEntry class to include a flag timestamp field if needed
            // For now, let's just return the original entry
            return entry
        }
    }
}

@Composable
fun MeetingFlagText(
    transcriptEntry: TranscriptEntry,
    modifier: Modifier = Modifier,
    onMeetingFlagAdded: (TranscriptEntry, Long) -> Unit
) {
    Column(modifier = modifier.verticalScroll()) {
        Text(
            text = transcriptEntry.sentence,
            modifier = modifier,
        )
        Text(
            text = "Flag Timestamp: ${MeetingFlagHandler.formatMeetingFlagTimestamp(transcriptEntry.timestamp)}",
            modifier = modifier,
        )
        Text(
            text = "Add Meeting Flag",
            modifier = modifier.clickable {
                // Simulate add meeting flag current transcript entry
                val currentTimeMillis = System.currentTimeMillis()
                onMeetingFlagAdded(transcriptEntry, currentTimeMillis)
            }
        )
    }
}