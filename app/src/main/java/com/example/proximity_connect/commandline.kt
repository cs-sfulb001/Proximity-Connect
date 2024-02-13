import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Define data class represent each entry in the transcript
data class TranscriptEntry(val timestamp: Long, val sentence: String)

// Define object to handle timestamp formatting
object TimestampHandler {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    // Function to format timestamp
    fun formatTimestamp(timestamp: Long): String {
        val date = Date(timestamp)
        return dateFormat.format(date)
    }
}

fun main() {
    val transcript = mutableListOf<TranscriptEntry>()

    // Interactive input for adding conversation lines with timestamps
    println("Enter new conversation lines with timestamps (type 'exit' to finish):")
    var input: String?
    do {
        print("Timestamp (in milliseconds): ")
        val timestampInput = readLine()?.toLongOrNull()

        if (timestampInput != null) {
            print("Sentence: ")
            input = readLine()
            if (!input.equals("exit", ignoreCase = true)) {
                transcript.add(TranscriptEntry(timestampInput, input ?: ""))
            }
        } else {
            input = readLine()
        }
    } while (input?.toLowerCase() != "exit")

    // Display conversation transcript with timestamps
    println("\nConversation Transcript with Timestamps:")
    transcript.forEach { entry ->
        val timestamp = TimestampHandler.formatTimestamp(entry.timestamp)
        println("$timestamp: ${entry.sentence}")
    }
}