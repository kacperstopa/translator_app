import grpc

import translator_pb2_grpc
import translator_pb2

def run():
  channel = grpc.insecure_channel('localhost:50051')
  stub = translator_pb2_grpc.TranslatorStub(channel)
  response = stub.DetectLanguage(translator_pb2.DetectLanguageRequest(text='Witaj świecie'))
  print(response.language)

run()