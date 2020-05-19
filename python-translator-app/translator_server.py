from concurrent import futures
from googletrans import Translator

import logging

import grpc

import translator_pb2
import translator_pb2_grpc

class TranslatorServicer(translator_pb2_grpc.TranslatorServicer):
    def __init__(self):
        self.translator = Translator()

    def Translate(self, request, context):
        return translator_pb2.TranslateReply(text = self.translator.translate(request.text, src=request.fromLanguage, dest=request.toLanguage).text)

    def DetectLanguage(self, request, context):
        return translator_pb2.DetectLanguageReply(language = self.translator.detect(request.text).lang)

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    translator_pb2_grpc.add_TranslatorServicer_to_server(
        TranslatorServicer(), server)
    server.add_insecure_port('0.0.0.0:8081')
    server.start()
    server.wait_for_termination()


if __name__ == '__main__':
    logging.basicConfig()
    serve()
