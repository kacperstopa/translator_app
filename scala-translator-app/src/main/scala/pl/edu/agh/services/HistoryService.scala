package pl.edu.agh.services

import cats.effect.ConcurrentEffect
import cats.implicits._
import pl.edu.agh.model.TranslationDTO

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
trait HistoryService[F[_]] {
  def saveTranslation(translation: TranslationDTO): F[Unit]
  def getRecentTranslations: F[List[TranslationDTO]]
}

class HistoryServiceImpl[F[_]: ConcurrentEffect]() extends HistoryService[F] {
  val translations: mutable.ListBuffer[TranslationDTO] = ListBuffer.empty

  override def saveTranslation(translation: TranslationDTO): F[Unit] =
    translations.prepend(translation).pure[F].map(_ => ())

  override def getRecentTranslations: F[List[TranslationDTO]] =
    translations.take(5).toList.pure[F]
}
