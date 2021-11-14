package dataPipe.encoding

trait ListEncoder {
  def encode(value: List[String]): String
}
