import assemblyai as aai
import random
from datetime import datetime

class Message:
    def __init__(self, username, message, time, pinned=False):
        self.username = username
        self.message = message
        self.time = time
        self.flags = {}
        self.pinned = pinned

    def __str__(self):
        flag_str = '\n'.join([f"Line {line_number}: Flags: {', '.join(flags)}" for line_number, flags in self.flags.items()])
        return f"{self.time} {self.username}: {self.message}\n{flag_str}\n"


    def setUsername(self, name):
        self.username = name

    def getUsername(self):
        return self.username

    def setMessage(self, newMessage):
        self.message = newMessage

    def getMessage(self):
        return self.message

    def setTime(self, newTime):
        self.time = newTime

    def getTime(self):
        return self.time

    def addFlag(self, line_number, flag):
        if line_number in self.flags:
            self.flags[line_number].append(flag)
        else:
            self.flags[line_number] = [flag]

    # def searchFlags(self):
    #     flagged_messages = [line_number for line_number, flags in self.flags.items() if "Important" in flags]
    #     return flagged_messages


aai.settings.api_key = "353ff195df9d4243b247c2ecb4b80b46"

username = "Brianna"

clips = ["soundFiles/burger-king-foot-lettuce.mp3", "soundFiles/no-pomegranates.mp3"]

randomFile = random.choice(clips)

FILE_URL = randomFile

transcriber = aai.Transcriber()
transcript = transcriber.transcribe(FILE_URL)

now = datetime.now()
time = now.strftime("%H:%M:%S")
message = transcript.text

myMessage = Message(username, message, time, False)

# Split the transcript into lines and add line numbers
lines = transcript.text.split('\n')
for i, line in enumerate(lines):
    myMessage.addFlag(i, line)

# Print Transcript
print("Transcript:")
lines = transcript.text.split('\n')
for i, line in enumerate(lines):
    print(f"LINE {i} : {now.strftime('%H:%M:%S')} : {username} : {line}")
print()

# myMessage = Message(username, transcript.text, now.strftime("%H:%M:%S"), False)

# Add meeting flags to messages
# myMessage.addFlag("Important")
# myMessage.addFlag("Action Item")


# Add meeting flags to transcript lines
print("Add meeting flags to transcript lines (enter line number, or 'exit' to finish):")
while True:
    input_line = input("Line number: ")
    if input_line.lower() == 'exit':
        break
    try:
        line_number = int(input_line)
        if 0 <= line_number < len(lines):
            flag = input("Enter flag (leave blank if none): ")
            myMessage.addFlag(line_number, flag)
            print(f"Flag '{flag}' added at line {line_number}.")
        else:
            print("Invalid line number. Please enter a valid line number or 'exit' to finish.")
    except ValueError:
        print("Invalid input. Please enter a valid line number or 'exit' to finish.")

# Display flagged messages
print("\nFlagged Messages:")
for line_number, flags in myMessage.flags.items():
    if flags:
        print(f"Line {line_number}: {lines[line_number]}")

