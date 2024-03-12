import assemblyai as aai
import random
from datetime import datetime

class Message:
    def __init__(self, username, message, time):
        self.username = username
        self.message = message
        self.time = time
        self.flags = []

    def __str__(self):
        return f"{self.time} {self.username}: {self.message} {'Flags: ' + ', '.join(self.flags) if self.flags else ''}\n"

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

    def addFlag(self, flag):
        self.flags.append(flag)

def searchFlags(messages, flag):
    flagged_messages = [msg for msg in messages if flag in msg.flags]
    return flagged_messages

aai.settings.api_key = "353ff195df9d4243b247c2ecb4b80b46"

USERNAME = "Brianna"

clips = ["soundFiles/burger-king-foot-lettuce.mp3", "soundFiles/road-work-ahead-made-with-Voicemod.mp3"]

randomFile = random.choice(clips)

FILE_URL = randomFile

transcriber = aai.Transcriber()
transcript = transcriber.transcribe(FILE_URL)

if transcript.status == aai.TranscriptStatus.error:
    print(transcript.error)
else:
    print(USERNAME + ": " + transcript.text)

now = datetime.now()

myMessage = Message(USERNAME, transcript.text, now.strftime("%H:%M:%S"))

# Add meeting flags to messages
myMessage.addFlag("Important")
myMessage.addFlag("Action Item")

print(myMessage)

# Example of searching for flagged messages
flagged_messages = searchFlags([myMessage], "Important")
print("Flagged Messages:")
for msg in flagged_messages:
    print(msg)