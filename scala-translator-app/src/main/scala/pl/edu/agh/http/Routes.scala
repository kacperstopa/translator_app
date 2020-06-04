package pl.edu.agh.http

import cats.effect.Sync
import cats.implicits._
import org.http4s.{EntityDecoder, EntityEncoder, HttpRoutes}
import org.http4s.circe.jsonOf
import org.http4s.dsl.Http4sDsl
import pl.edu.agh.model.{DetectRequestDTO, TranslateRequestDTO, TranslationDTO}
import pl.edu.agh.services.{HistoryService, TranslationService}
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.circe._

object Routes {
  def routes[F[_]: Sync](translationService: TranslationService[F],
                         historyService: HistoryService[F],
  ): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._

    implicit val translateDTODecoder: EntityDecoder[F, TranslateRequestDTO] =
      jsonOf[F, TranslateRequestDTO]

    implicit val detectDTODecoder: EntityDecoder[F, DetectRequestDTO] =
      jsonOf[F, DetectRequestDTO]

    HttpRoutes.of[F] {
      case req @ POST -> Root / "translate" =>
        for {
          translateDTO <- req.as[TranslateRequestDTO]
          translation <- translationService.translate(
                   translateDTO.text,
                   translateDTO.from,
                   translateDTO.to
          )
          _ <- historyService.saveTranslation(TranslationDTO(
            translateDTO.from,
            translateDTO.to,
            translateDTO.text,
            translation
          ))
          response <- Ok(translation)
        } yield response
      case req @ POST -> Root / "detect" =>
        for {
          detectDTO <- req.as[DetectRequestDTO]
          detectedLanguage <- translationService.detectLanguage(detectDTO.text)
          response <- Ok(detectedLanguage)
        } yield response
      case GET -> Root / "history" =>
        for {
          translations <- historyService.getRecentTranslations
          response <- Ok(translations.asJson)
        } yield response
    }
  }
}
