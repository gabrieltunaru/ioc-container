package framework.config

import framework.utils.FileReader
import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import io.circe.parser.decode

object ConfigReader {

  def readConfig(path: String): List[ConfigEntry] = {
    val stringData = FileReader.readAll(path)

    implicit val nestedDecoder: Decoder[ConfigEntry] =
      deriveDecoder[ConfigEntry]

    val decodedE = decode[List[ConfigEntry]](stringData)
    decodedE match {
      case Left(e)      => throw new Error(s"invalid config file $e")
      case Right(value) => value
    }
  }
}
