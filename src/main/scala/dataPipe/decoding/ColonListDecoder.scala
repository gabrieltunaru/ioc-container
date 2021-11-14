package dataPipe.decoding
class ColonListDecoder extends ListDecoder {
  override def decode(value: String): List[String] = value.split(":").toList
}
