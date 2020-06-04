package pl.edu.agh.model

case class TranslateRequestDTO(from: String, to: String, text: String)
case class DetectRequestDTO(text: String)
case class TranslationDTO(from: String, to: String, fromText: String, toText: String)
