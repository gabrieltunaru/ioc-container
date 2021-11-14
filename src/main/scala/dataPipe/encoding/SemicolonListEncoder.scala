package dataPipe.encoding

class SemicolonListEncoder extends ListEncoder {
  override def encode(value: List[String]): String =
    value.reduce((a, b) => s"$a;$b")
}
