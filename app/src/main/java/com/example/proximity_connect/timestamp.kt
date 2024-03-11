import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
            return entry.copy(flagTimestamp = flagTimestamp)
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
            text = "Flag Timestamp: ${
                if (transcriptEntry.flagTimestamp != null)
                    MeetingFlagHandler.formatMeetingFlagTimestamp(transcriptEntry.flagTimestamp!!)
                else "No Flag"
            }",
            modifier = modifier,
        )
        if (transcriptEntry.flagTimestamp == null) {
            Text(
                text = "Add Meeting Flag",
                modifier = modifier.clickable {
                    // Simulate add meeting flag to current transcript entry
                    val currentTimeMillis = System.currentTimeMillis()
                    onMeetingFlagAdded(transcriptEntry, currentTimeMillis)
                }
            )
        }
    }
}