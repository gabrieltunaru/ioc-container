package dataPipe

import dataPipe.decoding.ListDecoder
import dataPipe.encoding.ListEncoder

class DataPipe(decoder: ListDecoder, encoder: ListEncoder) {
  def pipe(value: String): String = encoder.encode(decoder.decode(value))
}
