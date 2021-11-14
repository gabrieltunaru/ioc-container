package framework.utils

import java.io.File
import scala.io.Source

object FileReader {

  private def open(path: String) = new File(path)

  private implicit class RichFile(file: File) {
    def read(): String = {
      val source = Source.fromFile(file)
      val lines = source.getLines().mkString
      source.close()
      lines
    }
  }

  def readAll(path: String): String = {
    open(path).read()
  }
}
