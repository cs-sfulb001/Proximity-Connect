import assemblyai as aai
import random
from datetime import datetime

class TranscriptEntry:
    def __init__(self, sentence, flagged=False):
        self.sentence = sentence
        self.flagged = flagged

    def __str__(self):
        return self.sentence

def transcribe_audio(file_path):
    transcriber = aai.Transcriber()
    transcript = transcriber.transcribe(file_path)
    return transcript.text.split('\n')

def display_transcript(transcript):
    print("Transcript:")
    for i, line in enumerate(transcript, start=1):
        print(f"{i}: {line}")

def add_flag(transcript, line_number):
    if 0 < line_number <= len(transcript):
        transcript[line_number - 1].flagged = True
        print(f"Flag added at line {line_number}")
    else:
        print("Invalid line number")

def search_flags(transcript):
    flagged_lines = [entry for entry in transcript if entry.flagged]
    if flagged_lines:
        print("\nFlagged Messages:")
        for i, entry in enumerate(flagged_lines, start=1):
            print(f"Line {i}: {entry}")
    else:
        print("\nNo flagged messages found.")

def main():
    aai.settings.api_key = "353ff195df9d4243b247c2ecb4b80b46"
    username = "Brianna"

    clips = ["soundFiles/burger-king-foot-lettuce.mp3", "soundFiles/no-pomegranates.mp3"]
    randomFile = random.choice(clips)

    transcript = [TranscriptEntry(sentence) for sentence in transcribe_audio(randomFile)]

    display_transcript(transcript)

    while True:
        choice = input("\nAdd flag (A), Search flags (S), or Exit (E): ").upper()
        if choice == 'A':
            line_number = int(input("Enter line number to add flag: "))
            add_flag(transcript, line_number)
        elif choice == 'S':
            search_flags(transcript)
        elif choice == 'E':
            break
        else:
            print("Invalid choice.")

if __name__ == "__main__":
    main()