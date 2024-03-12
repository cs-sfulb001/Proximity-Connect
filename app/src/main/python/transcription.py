import assemblyai as aai
import random
from datetime import datetime

class Message:
    def __init__(self, username, message, time, pinned):
        self.username = username
        self.message = message
        self.time = time
        self.pinned = pinned

    def __str__(self):
        return f"{self.time} {self.username}: {self.message} \n"

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
    def setPinned(self, pin):
        self.pinned = pin
    def getPinned(self):
        return self.pinned

aai.settings.api_key = "353ff195df9d4243b247c2ecb4b80b46"

username = "Brianna"

clips = ["soundFiles/burger-king-foot-lettuce.mp3", "soundFiles/road-work-ahead-made-with-Voicemod.mp3", "soundFiles/no-pomegranates.mp3"]

randomFile = random.choice(clips)

# URL of the file to transcribe
FILE_URL = randomFile

# You can also transcribe a local file by passing in a file path
# FILE_URL = './path/to/file.mp3'

transcriber = aai.Transcriber()
transcript = transcriber.transcribe(FILE_URL)

now = datetime.now()
time = now.strftime("%H:%M:%S")
message = transcript.text

myMessage = Message(username, message, time, False)

f = open("outBoundMessage.txt", "a")
f.write(myMessage.__str__() + "\n")
f.close()

if transcript.status == aai.TranscriptStatus.error:
    print(transcript.error)
else:
    print(myMessage)

