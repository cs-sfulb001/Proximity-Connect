import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.clickable
import com.example.proximity_connect.TranscriptEntry
import java.text.SimpleDateFormat
import java.util.*

class TimestampHandler {

    companion object {
        fun formatTimestamp(timestamp: Long): String {
            val date = Date(timestamp)
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            return format.format(date)
        }

        // Function to add timestamp to a transcript entry
        fun addTimestamp(entry: TranscriptEntry, timestamp: Long): TranscriptEntry {
            return entry.copy(timestamp = timestamp)
        }
    }
}

@Composable
fun TimestampText(
    transcriptEntry: TranscriptEntry,
    modifier: Modifier = Modifier,
    onTimestampAdded: (TranscriptEntry, Long) -> Unit
) {
    Column(modifier = modifier.verticalScroll()) {
        Text(
            text = transcriptEntry.sentence,
            modifier = modifier,
        )
        Text(
            text = "Timestamp: ${TimestampHandler.formatTimestamp(transcriptEntry.timestamp)}",
            modifier = modifier,
        )
        Text(
            text = "Add Timestamp",
            modifier = modifier.clickable {
                // Simulate add timestamp at current transcript entry
                val currentTimeMillis = System.currentTimeMillis()
                onTimestampAdded(transcriptEntry, currentTimeMillis)
            }
        )
    }
}