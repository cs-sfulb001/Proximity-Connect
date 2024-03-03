import java.util.*

data class TranscriptEntry(val flagTimestamp: Long, val sentence: String)

fun main() {
    val transcript = mutableListOf<TranscriptEntry>()

    // Interactive input adding conversation lines with meeting flags
    println("Enter new conversation lines with meeting flags (type 'exit' to finish):")
    var input: String?
    do {
        print("Flag Timestamp (in milliseconds): ")
        val flagTimestampInput = readLine()?.toLongOrNull()

        if (flagTimestampInput != null) {
            print("Sentence: ")
            input = readLine()
            if (!input.equals("exit", ignoreCase = true)) {
                transcript.add(TranscriptEntry(flagTimestampInput, input ?: ""))
            }
        } else {
            input = readLine()
        }
    } while (input?.toLowerCase() != "exit")

    // Display transcript with meeting flags
    println("\nConversation Transcript with Meeting Flags:")
    transcript.forEach { entry ->
        println("${Date(entry.flagTimestamp)}: ${entry.sentence}")
    }
}