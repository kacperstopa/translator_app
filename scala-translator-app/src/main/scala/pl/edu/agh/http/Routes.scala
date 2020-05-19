package pl.edu.agh.http

import cats.effect.Sync
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import pl.edu.agh.services.TranslationService

object Routes {
  def routes[F[_]: Sync](
    translationService: TranslationService[F]
  ): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._

    HttpRoutes.of[F] {
      case GET -> Root / text =>
        for {
          translation <- translationService.translate(text, "polish", "english")
          response <- Ok(translation)
        } yield response
    }
  }
}
