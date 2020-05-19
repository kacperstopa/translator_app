package pl.edu.agh.services

trait TranslationService[F[_]] {
  def translate(text: String, from: String, to: String): F[String]
  def detectLanguage(text: String): F[String]
}
