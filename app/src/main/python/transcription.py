import assemblyai as aai

aai.settings.api_key = "353ff195df9d4243b247c2ecb4b80b46"

USERNAME = "Brianna"

clips = ["Ford", "Volvo", "BMW"]

# URL of the file to transcribe
FILE_URL = r"C:\Users\Brianna\Desktop\SeniorProject\Proximity-Connect\app\src\main\python\burger-king-foot-lettuce.mp3"

# You can also transcribe a local file by passing in a file path
# FILE_URL = './path/to/file.mp3'

transcriber = aai.Transcriber()
transcript = transcriber.transcribe(FILE_URL)

if transcript.status == aai.TranscriptStatus.error:
    print(transcript.error)
else:
    print(USERNAME + ": " + transcript.text)