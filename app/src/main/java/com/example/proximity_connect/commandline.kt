import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Define data class representing each entry in the transcript
data class TranscriptEntry(val flagTimestamp: Long, val sentence: String)

// Define object to handle timestamp formatting
object TimestampHandler {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    // Function to format timestamp
    fun formatTimestamp(timestamp: Long): String {
        val date = Date(timestamp)
        return dateFormat.format(date)
    }

    // Function to search for meeting flags
    fun searchMeetingFlags(transcript: List<TranscriptEntry>): List<TranscriptEntry> {
        return transcript.filter { it.sentence.contains("[FLAG]", ignoreCase = true) }
    }
}

fun main() {
    val transcript = mutableListOf<TranscriptEntry>()

    // Sample conversation
    val sampleConversation = listOf(
        "Student 1: Hey, have you all decided on which programming language to use for our project?",
        "Student 2: Not yet. I'm still leaning towards Kotlin.",
        "Student 3: Really? I thought Java would be a better choiceconsidering our team's familiarity with it.",
        "Student 1: That's a valid point. Kotlin does offer some advantages, though.",
        "Student 4: Yeah, but Kotlin has better compatibility with Java libraries.",
        "Student 2: True. And its concise syntax can save us a lot of time.",
        "Student 3: But Java has been around longer and a larger community.",
        "Student 1: We should also consider the learning curve for each language.",
        "Student 4: Agreed. We don't want to spend too much time learning a new language.",
        "Student 2: OK, I think we've covered most of the pros and cons. So, what's our final decision?",
        "Student 1: Let's agree on Java for now, and revisit later if we need to."
    )

    // Display sample conversation
    println("Sample Conversation:")
    sampleConversation.forEachIndexed { index, sentence ->
        println("$index: $sentence")
    }
    println()

    // Interactive input adding meeting flags to sample conversation lines
    println("Add meeting flags to sample conversation lines (enter line number and press Enter to add flag, or 'exit' to finish):")
    var input: String?
    do {
        print("Line number: ")
        input = readLine()

        if (!input.equals("exit", ignoreCase = true)) {
            val lineNumber = input?.toIntOrNull()
            if (lineNumber != null && lineNumber >= 0 && lineNumber < sampleConversation.size) {
                val flagTimestamp = System.currentTimeMillis()
                transcript.add(TranscriptEntry(flagTimestamp, sampleConversation[lineNumber] + " [FLAG]"))
                println("Flag added at line $lineNumber")
            } else {
                println("Invalid line number. Please enter a valid line number or 'exit' to finish.")
            }
        }
    } while (!input.equals("exit", ignoreCase = true))

    // Prompt the user to search for meeting flags
    println("\nSearch for meeting flags in the transcript? (yes/no)")
    input = readLine()
    if (input.equals("yes", ignoreCase = true)) {
        val meetingFlags = transcript.filter { it.sentence.contains("[FLAG]", ignoreCase = true) }
        if (meetingFlags.isNotEmpty()) {
            println("\nMeeting flags found in the transcript:")
            meetingFlags.forEachIndexed { index, entry ->
                println("${index + 1}: ${entry.sentence}")
            }
        } else {
            println("\nNo meeting flags found in the transcript.")
        }
    }
}