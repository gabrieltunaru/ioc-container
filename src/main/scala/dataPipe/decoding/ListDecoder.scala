package dataPipe.decoding

trait ListDecoder {
  def decode(value: String): List[String]
}
