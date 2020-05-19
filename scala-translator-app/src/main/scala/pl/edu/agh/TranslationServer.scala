package pl.edu.agh

import cats.effect.{ConcurrentEffect, ContextShift, Timer}
import fs2._
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.middleware.{CORS, Logger}
import pl.edu.agh.grpc.GrpcTranslationService
import pl.edu.agh.http.Routes
import pl.edu.agh.services.TranslationService

import scala.concurrent.ExecutionContext.global

object TranslationServer {
  def stream[F[_]: ConcurrentEffect](implicit T: Timer[F],
                                     C: ContextShift[F]): Stream[F, Nothing] = {
    for {
      client <- BlazeClientBuilder[F](global).stream

      translationService: TranslationService[F] = new GrpcTranslationService[F]

      httpApp = (
        Routes.routes[F](translationService)
      ).orNotFound

      finalHttpApp = Logger.httpApp(true, true)(httpApp)

      exitCode <- BlazeServerBuilder[F]
        .bindHttp(8080, "0.0.0.0")
        .withHttpApp(CORS(finalHttpApp))
        .serve
    } yield exitCode
  }.drain
}
