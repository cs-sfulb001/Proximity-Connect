// Define data class represent each entry in the transcript
data class TranscriptEntry(val timestamp: Long, val sentence: String)

// Sample transcript
val transcript = listOf(
    TranscriptEntry(0, "Hello, how are you?"),
    TranscriptEntry(5000, "I'm doing well, thank you."),
    TranscriptEntry(10000, "What about you?")
)

// Display transcript with timestamps
@Composable
fun TranscriptWithTimestamps(transcript: List<TranscriptEntry>) {
    transcript.forEach { entry ->
        Row {
            TimestampText(timestamp = entry.timestamp)
            Text(text = entry.sentence)
        }
    }
}

// usage in UI
@Composable
fun YourUIComponent() {
    // Call the function to display the transcript with timestamps
    TranscriptWithTimestamps(transcript = transcript)
}