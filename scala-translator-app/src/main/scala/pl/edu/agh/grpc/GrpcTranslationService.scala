package pl.edu.agh.grpc

import java.net.SocketAddress

import cats.effect.ConcurrentEffect
import io.grpc.Channel
import io.grpc.netty.NettyChannelBuilder
import monix.eval.Task
import monix.execution.Scheduler
import pl.edu.agh.services.TranslationService
import translator.{DetectLanguageRequest, TranslateRequest, TranslatorGrpc}

class GrpcTranslationService[F[_]: ConcurrentEffect]
    extends TranslationService[F] {
  implicit val scheduler = Scheduler.global

  val channel: Channel = NettyChannelBuilder
    .forTarget("localhost:8081")
    .usePlaintext()
    .build

  val stub: TranslatorGrpc.TranslatorStub = TranslatorGrpc.stub(channel)

  def translate(text: String, from: String, to: String): F[String] =
    Task
      .deferFuture(stub.translate(TranslateRequest(text, from, to)))
      .map(_.text)
      .toConcurrent

  def detectLanguage(text: String): F[String] =
    Task
      .deferFuture(stub.detectLanguage(DetectLanguageRequest(text)))
      .map(_.language)
      .toConcurrent
}
